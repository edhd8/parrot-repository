package mx.com.mercadolibre.hansolo.app.controllers

import feign.Param
import mx.com.mercadolibre.hansolo.api.CommunicationsInterface
import mx.com.mercadolibre.hansolo.common.dto.Satellite
import mx.com.mercadolibre.hansolo.common.exceptions.ApiException
import mx.com.mercadolibre.hansolo.common.request.Satellites
import mx.com.mercadolibre.hansolo.common.routes.Route
import mx.com.mercadolibre.hansolo.core.services.ResistanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
    private lateinit var resistanceService: ResistanceService

    @GetMapping(value = [Route.Resistance.LOCATION])
    override fun getLocation(@Param("distances") distances: DoubleArray) =
        ResponseEntity.status(HttpStatus.OK).body(resistanceService.getLocation(distances))

    @GetMapping(value = [Route.Resistance.MESSAGE])
    override fun getMessage(@RequestBody satellites: Satellites) =
        ResponseEntity.status(HttpStatus.OK).body(resistanceService.getMessage(satellites))

    @PostMapping(value = [Route.Resistance.TOP_SECRET])
    override fun calculateInfo(@RequestBody satellites: Satellites): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(resistanceService.calculateInfo(satellites))
        } catch (e: ApiException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }

    @PostMapping(value = [Route.Resistance.TOP_SECRET_SPLIT])
    override fun saveInfo(@PathVariable("satellite_name") satelliteName: String,
                          @RequestBody satellite: Satellite): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(resistanceService.saveInfo(satelliteName, satellite))
        } catch (e: ApiException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }

    @GetMapping(value = [Route.Resistance.TOP_SECRET_SPLIT])
    override fun getInfo(@PathVariable("satellite_name") satelliteName: String,
                         @RequestBody satellite: Satellite): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(resistanceService.getInfo(satelliteName, satellite))
        } catch (e: ApiException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }
}