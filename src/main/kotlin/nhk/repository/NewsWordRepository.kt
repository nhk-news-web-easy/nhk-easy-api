package nhk.repository

import nhk.entity.NewsWord
import org.springframework.data.jpa.repository.JpaRepository

interface NewsWordRepository : JpaRepository<NewsWord, Int> {
    fun findByNewsId(newsId: Int): List<NewsWord>
}
