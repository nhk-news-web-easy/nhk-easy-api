package nhk.controller.api

import nhk.controller.BaseController
import nhk.dto.NewsDto
import nhk.repository.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class NewsController : BaseController() {
    @Autowired
    lateinit var newsRepository: NewsRepository

    @GetMapping("/news")
    fun getNews(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startDate: Date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endDate: Date): List<NewsDto> {
        return newsRepository.findByPublishedAtUtcBetween(startDate.toInstant(), endDate.toInstant())
                .map { news ->
                    val dto = NewsDto()
                    dto.newsId = news.newsId
                    dto.title = news.title
                    dto.titleWithRuby = news.titleWithRuby
                    dto.outlineWithRuby = news.outlineWithRuby
                    dto.body = news.body
                    dto.url = news.url
                    dto.m3u8Url = news.m3u8Url
                    dto.imageUrl = news.imageUrl
                    dto.publishedAtUtc = news.publishedAtUtc

                    dto
                }
    }
}