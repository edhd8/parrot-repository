package mx.com.mercadolibre.hansolo.app.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import mx.com.mercadolibre.hansolo.common.routes.Route
import org.springframework.beans.factory.annotation.Value

@RestController
@RequestMapping(value = [(Route.BASE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class HealthCheckController {

    @Value("\${spring.application.name}")
    private lateinit var name: String

    @GetMapping(value = [Route.HEALTH])
    @ResponseBody
    fun getHealthCheck(): ResponseEntity<HashMap<String, String>> =
        ResponseEntity(hashMapOf("[${name}]" to "Status of the microservice is UP."), HttpStatus.OK)

}