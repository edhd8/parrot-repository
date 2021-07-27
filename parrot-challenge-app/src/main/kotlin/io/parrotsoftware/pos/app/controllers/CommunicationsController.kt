package io.parrotsoftware.pos.app.controllers

import io.parrotsoftware.pos.api.CommunicationsInterface
import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.common.exceptions.ApiException
import io.parrotsoftware.pos.common.routes.Route
import io.parrotsoftware.pos.core.services.CoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = [(Route.BASE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class CommunicationsController : CommunicationsInterface {

    @Autowired
    private lateinit var coreService: CoreService

    @PostMapping(value = [Route.Core.NEW_USER])
    override fun saveUser(@PathVariable("user_name") userName: String,
                          @RequestBody user: User): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(coreService.saveUser(userName, user))
        } catch (e: ApiException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }
}