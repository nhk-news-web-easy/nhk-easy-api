package nhk.repository

import io.github.io.github.nhk_news_web_easy.WordDefinition
import org.springframework.data.jpa.repository.JpaRepository

interface WordDefinitionRepository : JpaRepository<WordDefinition, Int> {
    fun findByWordIdIn(wordIds: List<Int>): List<WordDefinition>
}
