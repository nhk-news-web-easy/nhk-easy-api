package nhk.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.github.io.github.nhk_news_web_easy.News
import nhk.BaseTest
import nhk.dto.NewsDto
import nhk.repository.NewsRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant

class NewsControllerTest : BaseTest() {
    @Autowired
    private lateinit var newsRepository: NewsRepository

    @Test
    fun shouldReturnBadRequestWhenGetNews() {
        mockMvc.perform(MockMvcRequestBuilders.get("/news"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2018-04-04T16:00:00.000Z&endDate=2018-03-04T16:00:00.000Z"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
        mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2018-04-04T16:00:00.000Z&endDate=2018-06-04T16:00:00.000Z"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun shouldReturnNews() {
        val news = News()
        news.body = "body"
        news.newsId = "1"
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

        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())

        val body = mockMvc.perform(MockMvcRequestBuilders.get("/news?startDate=2023-01-25T00:00:00.000Z&endDate=2023-01-25T23:59:59.000Z"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString
        val newsDtos = objectMapper.readValue(body, object : TypeReference<List<NewsDto>>() {})

        Assertions.assertTrue(newsDtos.isNotEmpty())
    }
}
