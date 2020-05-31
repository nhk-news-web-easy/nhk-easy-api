package nhk.repository

import nhk.entity.WordDefinition
import org.springframework.data.jpa.repository.JpaRepository

interface WordDefinitionRepository : JpaRepository<WordDefinition, Int>