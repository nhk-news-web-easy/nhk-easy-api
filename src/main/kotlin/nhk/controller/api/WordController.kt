package nhk.controller.api

import nhk.controller.BaseController
import nhk.dto.WordDefinitionDto
import nhk.dto.WordDto
import nhk.repository.NewsRepository
import nhk.repository.NewsWordRepository
import nhk.repository.WordDefinitionRepository
import nhk.repository.WordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WordController : BaseController() {
    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var newsWordRepository: NewsWordRepository

    @Autowired
    lateinit var wordRepository: WordRepository

    @Autowired
    lateinit var wordDefinitionRepository: WordDefinitionRepository

    @GetMapping("words")
    fun getWords(@RequestParam newsId: String): List<WordDto> {
        val news = newsRepository.findByNewsId(newsId)

        if (news.isEmpty()) {
            return emptyList()
        }

        val newsWords = newsWordRepository.findByNewsId(news.first().id)
        val newsWordMap = newsWords.map { newsWord ->
            newsWord.wordId to newsWord
        }.toMap()
        val wordIds = newsWords.map { newsWord ->
            newsWord.wordId
        }
        val words = wordRepository.findByIdIn(wordIds)
        val wordDefinitions = wordDefinitionRepository.findByWordIdIn(wordIds)
                .groupBy { wordDefinition ->
                    wordDefinition.wordId
                }

        return words.map { word ->
            val wordDto = WordDto()
            wordDto.idInNews = newsWordMap[word.id]?.idInNews ?: ""
            wordDto.name = word.name
            wordDto.definitions = wordDefinitions[word.id]
                    ?.map { wordDefinition ->
                        val wordDefinitionDto = WordDefinitionDto()
                        wordDefinitionDto.definition = wordDefinition.definition
                        wordDefinitionDto.definitionWithRuby = wordDefinition.definitionWithRuby

                        wordDefinitionDto
                    }
                    ?.toMutableList() ?: mutableListOf()

            wordDto
        }
    }
}