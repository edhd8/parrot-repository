package io.parrotsoftware.pos.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")

data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = "",

    @Column(columnDefinition = "TEXT")
    var products: String = "",

    @Column(columnDefinition = "TEXT")
    var quantities: String = "",

    @Column(columnDefinition = "TEXT")
    var subtotals: String = "",

    var totalPrice: Double? = 0.0,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Timestamp = Timestamp(System.currentTimeMillis())
)