package nhk.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import nhk.dto.TopNewsDto
import nhk.entity.News
import nhk.entity.Word
import nhk.entity.WordDefinition
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class NewsParser {
    fun parseNews(topNews: TopNewsDto): News {
        val newsId = topNews.newsId
        val url = "https://www3.nhk.or.jp/news/easy/$newsId/$newsId.html"
        val document = Jsoup.connect(url).get()
        val body = document.getElementById("js-article-body")
        val links = body.select("a")
        links.forEach { link ->
            link.removeAttr("href")
        }

        val news = News()
        news.newsId = newsId
        news.title = topNews.title
        news.titleWithRuby = topNews.titleWithRuby
        news.outlineWithRuby = cleanUpOutline(topNews.outlineWithRuby)
        news.url = url
        news.body = body.html()
        news.imageUrl = when (topNews.hasNewsWebImage) {
            true -> topNews.newsWebImageUri
            false -> "https://www3.nhk.or.jp/news/easy/${topNews.newsId}/${topNews.newsEasyImageUri}"
        }
        news.m3u8Url = "https://nhks-vh.akamaihd.net/i/news/easy/${topNews.newsEasyVoiceUri}/master.m3u8"
        news.publishedAtUtc = ZonedDateTime.of(topNews.newsPrearrangedTime, ZoneId.of("+9")).toInstant()
        news.words = parseWords(newsId)

        return news
    }

    private fun parseWords(newsId: String): MutableSet<Word> {
        val url = "https://www3.nhk.or.jp/news/easy/$newsId/$newsId.out.dic"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val json = response.body?.string()

        json?.let {
            val objectMapper = ObjectMapper()
            val root = objectMapper.readTree(it)
            val reikai = root.get("reikai")
            val entries = reikai.get("entries")

            return entries.fields()
                .asSequence()
                .flatMap { entry ->
                    parseWord(entry).asSequence()
                }.toMutableSet()
        }

        return mutableSetOf()
    }

    private fun parseWord(entry: Map.Entry<String, JsonNode>): List<Word> {
        return entry.value.toList()
            .groupBy { node -> node.get("hyouki")[0].asText() }
            .entries
            .map { keyValue ->
                val word = Word()
                word.name = keyValue.key
                word.idInNews = entry.key

                val wordDefinitions = keyValue.value
                    .map { node ->
                        val wordDefinition = WordDefinition()
                        wordDefinition.definitionWithRuby = node.get("def").asText()
                        wordDefinition.definition = this.extractWordDefinition(wordDefinition.definitionWithRuby)

                        wordDefinition
                    }
                    .toMutableList()

                word.definitions = wordDefinitions

                word
            }
    }

    private fun extractWordDefinition(definitionWithRuby: String): String {
        val document = Jsoup.parse(definitionWithRuby)
        val rubies = document.select("ruby")

        rubies.forEach { ruby ->
            ruby.select("rt").remove()
        }

        return document.text()
    }

    private fun cleanUpOutline(outline: String): String {
        val document = Jsoup.parse(outline)
        val links = document.select("a")
        links.forEach { link ->
            link.removeAttr("href")
        }

        // Jsoup wraps html/body around outline,
        // so we get body first and return its inner html
        return document.select("body").html()
    }
}
