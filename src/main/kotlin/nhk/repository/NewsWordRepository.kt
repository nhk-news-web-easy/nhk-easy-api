package nhk.repository

import io.github.io.github.nhk_news_web_easy.NewsWord
import org.springframework.data.jpa.repository.JpaRepository

interface NewsWordRepository : JpaRepository<NewsWord, Int> {
    fun findByNewsId(newsId: Int): List<NewsWord>
}
