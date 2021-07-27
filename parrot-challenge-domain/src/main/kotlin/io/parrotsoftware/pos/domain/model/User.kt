package io.parrotsoftware.pos.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var email: String? = null,

    var name: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Timestamp = Timestamp(System.currentTimeMillis())
)