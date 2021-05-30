package mx.com.mercadolibre.hansolo.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "satellite")
data class Satellite(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,

    var distance: Double? = null,

    @Column(columnDefinition = "TEXT")
    var message: String = "",

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: Timestamp? = null
)