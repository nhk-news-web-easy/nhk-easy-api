package nhk.repository

import nhk.entity.Word
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Word, Int> {
    fun findByIdIn(ids: List<Int>): List<Word>
}
