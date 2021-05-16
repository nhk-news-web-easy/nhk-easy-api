package nhk.repository

import nhk.entity.WordDefinition
import org.springframework.data.jpa.repository.JpaRepository

interface WordDefinitionRepository : JpaRepository<WordDefinition, Int> {
    fun findByWordIdIn(wordIds: List<Int>): List<WordDefinition>
}
