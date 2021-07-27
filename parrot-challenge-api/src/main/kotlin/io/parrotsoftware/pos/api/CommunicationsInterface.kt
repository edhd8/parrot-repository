package io.parrotsoftware.pos.api

import io.parrotsoftware.pos.common.dto.User
import org.springframework.http.ResponseEntity

interface CommunicationsInterface {

    fun saveUser(userName: String, user: User): ResponseEntity<*>

}