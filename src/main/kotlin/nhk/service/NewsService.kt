package nhk.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.module.kotlin.readValue
import nhk.Constants
import nhk.dto.TopNewsDto
import nhk.entity.NewsWord
import nhk.repository.NewsRepository
import nhk.repository.NewsWordRepository
import nhk.repository.WordDefinitionRepository
import nhk.repository.WordRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
open class NewsService {
    private val logger: Logger = LoggerFactory.getLogger(NewsService::class.java)

    @Autowired
    lateinit var newsParser: NewsParser

    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var wordRepository: WordRepository

    @Autowired
    lateinit var wordDefinitionRepository: WordDefinitionRepository

    @Autowired
    lateinit var newsWordRepository: NewsWordRepository

    @Transactional
    open fun fetchAndSaveTopNews() {
        val topNews = getTopNews()
                .filter { news ->
                    newsRepository.findByTitle(news.title).isEmpty()
                }.map { news ->
                    newsParser.parseNews(news)
                }

        newsRepository.saveAll(topNews)

        val words = topNews.flatMap { news ->
            news.words
        }.distinctBy { word ->
            word.name
        }.map { word ->
            val currentWords = wordRepository.findByName(word.name)

            if (currentWords.isEmpty()) {
                word
            } else {
                val currentWord = currentWords.first()
                currentWord.definitions = word.definitions

                currentWord
            }
        }
        val newWords = words.filter { word ->
            word.id == 0
        }

        wordRepository.saveAll(words)

        newWords.forEach { word ->
            val definitions = word.definitions
                    .map { definition ->
                        definition.wordId = word.id

                        definition
                    }

            wordDefinitionRepository.saveAll(definitions)
        }

        val wordNameIdMap = words.associateBy({ it.name }, { it.id })
        val newsWords = topNews.flatMap { news ->
            news.words
                    .map { word ->
                        val newsWord = NewsWord()
                        newsWord.newsId = news.id
                        newsWord.wordId = wordNameIdMap.getOrDefault(word.name, 0)
                        newsWord.idInNews = word.idInNews

                        newsWord
                    }
        }

        newsWordRepository.saveAll(newsWords)
    }

    fun getTopNews(): List<TopNewsDto> {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(Constants.TOP_NEWS_URL)
                .build()
        val response = okHttpClient.newCall(request).execute()
        val json = response.body?.string()

        json?.let {
            val javaTimeModule = JavaTimeModule()
            val localDateTimeDeserializer = LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constants.NHK_NEWS_EASY_DATE_FORMAT))
            javaTimeModule.addDeserializer(LocalDateTime::class.java, localDateTimeDeserializer)

            val objectMapper = ObjectMapper()
            objectMapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            objectMapper.registerModule(javaTimeModule)

            return objectMapper.readValue(it)
        }

        return emptyList()
    }
}