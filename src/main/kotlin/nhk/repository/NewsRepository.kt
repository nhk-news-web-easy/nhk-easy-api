package nhk.repository

import io.github.io.github.nhk_news_web_easy.News
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant

interface NewsRepository : JpaRepository<News, Int> {
    fun findByNewsId(newsId: String): List<News>

    fun findByPublishedAtUtcBetween(start: Instant, end: Instant): List<News>
}
