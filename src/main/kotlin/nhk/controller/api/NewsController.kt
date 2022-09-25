package nhk.controller.api

import nhk.controller.BaseController
import nhk.dto.NewsDto
import nhk.repository.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class NewsController : BaseController() {
    @Autowired
    private lateinit var newsRepository: NewsRepository

    private val thirtyDaysInMilliseconds = 30 * 24 * 60 * 60 * 1000L

    @GetMapping("/news")
    fun getNews(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: Date,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: Date
    ): ResponseEntity<List<NewsDto>> {
        if (endDate.before(startDate)) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

        if (endDate.time - startDate.time > thirtyDaysInMilliseconds) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

        val newsList = newsRepository.findByPublishedAtUtcBetween(startDate.toInstant(), endDate.toInstant())
            .map { news ->
                val dto = NewsDto()
                dto.newsId = news.newsId
                dto.title = news.title
                dto.titleWithRuby = news.titleWithRuby
                dto.outline = news.outline
                dto.outlineWithRuby = news.outlineWithRuby
                dto.body = news.body
                dto.bodyWithoutRuby = news.bodyWithoutRuby
                dto.url = news.url
                dto.m3u8Url = news.m3u8Url
                dto.imageUrl = news.imageUrl
                dto.publishedAtUtc = news.publishedAtUtc

                dto
            }

        return ResponseEntity(newsList, HttpStatus.OK)
    }
}
