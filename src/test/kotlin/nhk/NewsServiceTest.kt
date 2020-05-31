package nhk

import nhk.repository.NewsRepository
import nhk.repository.WordDefinitionRepository
import nhk.repository.WordRepository
import nhk.service.NewsParser
import nhk.service.NewsService
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class NewsServiceTest : BaseTest() {
    @Autowired
    lateinit var newsService: NewsService

    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var wordRepository: WordRepository

    @Autowired
    lateinit var wordDefinitionRepository: WordDefinitionRepository

    @Autowired
    lateinit var newsParser: NewsParser

    @Test
    fun shouldGetTopNews() {
        val topNews = newsService.getTopNews()

        Assert.assertTrue(topNews.isNotEmpty())
    }

    @Test
    fun shouldParseNewsAndWords() {
        val topNews = newsService.getTopNews()
        val news = newsParser.parseNews(topNews[0])

        Assert.assertNotNull(news)
        Assert.assertTrue(news.words.isNotEmpty())
    }

    @Test
    fun shouldSaveTopNews() {
        newsService.fetchAndSaveTopNews()

        val allNews = newsRepository.findAll()
        val allWords = wordRepository.findAll()
        val allWordDefinitions = wordDefinitionRepository.findAll()

        Assert.assertTrue(allNews.isNotEmpty())
        Assert.assertTrue(allWords.isNotEmpty())
        Assert.assertTrue(allWordDefinitions.isNotEmpty())
    }
}