package nhk.repository

import nhk.entity.Word
import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Word, Int> {
    fun findByName(name: String): List<Word>

    fun findByIdIn(ids: List<Int>): List<Word>
}
