package io.parrotsoftware.pos.api

import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.common.request.OrderRequest
import org.springframework.http.ResponseEntity

interface CommunicationsInterface {

    fun saveUser(userName: String, user: User): ResponseEntity<*>

    fun saveOrder(userName: String, order: OrderRequest): ResponseEntity<*>
}