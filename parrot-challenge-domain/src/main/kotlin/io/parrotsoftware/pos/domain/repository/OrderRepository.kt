package io.parrotsoftware.pos.domain.repository

import io.parrotsoftware.pos.domain.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp

@Repository
interface OrderRepository : JpaRepository<Order, Long> {

    fun findByName(name: String): Order?

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("SELECT * FROM public.orders WHERE created_at BETWEEN :from AND :to", nativeQuery = true)
    fun findBetween(
        @Param("from") from: Timestamp,
        @Param("to") to: Timestamp
    ): List<Order>?

}