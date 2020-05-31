package nhk.repository

import nhk.entity.News
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant

interface NewsRepository : JpaRepository<News, Int> {
    fun findByTitle(title: String): List<News>

    fun findByPublishedAtUtcBetween(start: Instant, end: Instant): List<News>
}