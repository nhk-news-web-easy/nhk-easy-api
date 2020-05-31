package nhk.entity

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Transient

@Entity
class Word : BaseEntity() {
    @Column(length = 50)
    var name = ""

    var createdAtUtc = Instant.now()

    var updatedAtUtc = Instant.now()

    @Transient
    var definitions = mutableListOf<WordDefinition>()

    @Transient
    var idInNews = ""
}