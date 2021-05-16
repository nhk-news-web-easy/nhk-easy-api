package nhk.dto

import java.time.LocalDateTime

class TopNewsDto {
    var topPriorityNumber = 0

    var topDisplayFlag = false

    var newsId = ""

    var newsPrearrangedTime = LocalDateTime.now()

    var title = ""

    var titleWithRuby = ""

    var outlineWithRuby = ""

    var newsFileVer = false

    var newsPublicationStatus = false

    var hasNewsWebImage = false

    var hasNewsWebMovie = false

    var hasNewsEasyImage = false

    var hasNewsEasyMovie = false

    var hasNewsEasyVoice = false

    var newsWebImageUri = ""

    var newsWebMovieUri = ""

    var newsEasyImageUri = ""

    var newsEasyMovieUri = ""

    var newsEasyVoiceUri = ""
}
