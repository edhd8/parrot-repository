package io.parrotsoftware.pos.domain.repository

import io.parrotsoftware.pos.domain.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {

    fun findByName(name: String): Order?

}