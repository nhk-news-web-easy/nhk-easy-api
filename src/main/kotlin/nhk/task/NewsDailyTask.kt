package nhk.task

import nhk.service.NewsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class NewsDailyTask {
    private val logger: Logger = LoggerFactory.getLogger(NewsDailyTask::class.java)

    @Autowired
    private lateinit var newsService: NewsService

    @Scheduled(cron = "0 0 10 * * *")
    fun saveTopNews() {
        val now = ZonedDateTime.now()

        logger.info("Start to fetch news, now={}", now)

        newsService.fetchAndSaveTopNews()
    }
}
