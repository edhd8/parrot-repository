package io.parrotsoftware.pos.domain.repository

import io.parrotsoftware.pos.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): User?

}