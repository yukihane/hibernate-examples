package examples.jpa.entity

import javax.persistence.Embeddable

@Embeddable
class AltId (
    var key1: Long,
    var key2: Long,
)