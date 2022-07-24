package nhk.repository

import io.github.io.github.nhk_news_web_easy.Word
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Word, Int> {
    fun findByIdIn(ids: List<Int>): List<Word>
}
