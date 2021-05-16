package nhk.entity

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Transient

@Entity
class News : BaseEntity() {
    @Column(length = 50)
    var newsId = ""

    @Column(length = 50)
    var title = ""

    @Column(length = 500)
    var titleWithRuby = ""

    @Column(length = 2000)
    var outlineWithRuby = ""

    @Column(columnDefinition = "text")
    var body = ""

    @Column(length = 200)
    var url = ""

    @Column(length = 200)
    var m3u8Url = ""

    @Column(length = 200)
    var imageUrl = ""

    var publishedAtUtc = Instant.now()

    @Transient
    var words = mutableSetOf<Word>()
}
