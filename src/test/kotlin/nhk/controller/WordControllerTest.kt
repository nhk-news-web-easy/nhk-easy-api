package nhk.controller

import io.github.io.github.nhk_news_web_easy.News
import io.github.io.github.nhk_news_web_easy.NewsWord
import io.github.io.github.nhk_news_web_easy.Word
import io.github.io.github.nhk_news_web_easy.WordDefinition
import nhk.BaseTest
import nhk.repository.NewsRepository
import nhk.repository.NewsWordRepository
import nhk.repository.WordDefinitionRepository
import nhk.repository.WordRepository
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant

class WordControllerTest : BaseTest() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var newsWordRepository: NewsWordRepository

    @Autowired
    lateinit var wordRepository: WordRepository

    @Autowired
    lateinit var wordDefinitionRepository: WordDefinitionRepository

    @Test
    fun shouldReturnBadRequestWhenGetWords() {
        mockMvc.perform(MockMvcRequestBuilders.get("/words"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldGetEmptyWords() {
        mockMvc.perform(MockMvcRequestBuilders.get("/words?newsId=999"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun shouldGetWords() {
        val news = News()
        news.body = "body"
        news.newsId = "2"
        news.url = "url"
        news.m3u8Url = "url"
        news.imageUrl = "image"
        news.bodyWithoutHtml = "body"
        news.outline = "outline"
        news.outlineWithRuby = "outline"
        news.title = "title"
        news.titleWithRuby = "title"
        news.publishedAtUtc = Instant.ofEpochMilli(1674615686093)

        newsRepository.save(news)

        val word = Word()
        word.name = "word"

        wordRepository.save(word)

        val wordDefinition = WordDefinition()
        wordDefinition.definition = "definition"
        wordDefinition.definitionWithRuby = "definition"
        wordDefinition.wordId = word.id

        wordDefinitionRepository.save(wordDefinition)

        val newsWord = NewsWord()
        newsWord.idInNews = "001"
        newsWord.wordId = word.id
        newsWord.newsId = news.id

        newsWordRepository.save(newsWord)

        mockMvc.perform(MockMvcRequestBuilders.get("/words?newsId=2"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("word")))
    }
}
