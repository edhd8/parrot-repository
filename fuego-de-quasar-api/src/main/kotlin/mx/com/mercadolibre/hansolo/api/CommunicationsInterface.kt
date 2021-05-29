package mx.com.mercadolibre.hansolo.api

import mx.com.mercadolibre.hansolo.common.dto.Satellite
import mx.com.mercadolibre.hansolo.common.request.Satellites
import mx.com.mercadolibre.hansolo.common.responses.InfoResponse
import org.springframework.http.ResponseEntity

interface CommunicationsInterface {

    fun getLocation(distances: DoubleArray): ResponseEntity<*>

    fun getMessage(satellites: Satellites): ResponseEntity<*>

    fun calculateInfo(satellites: Satellites): ResponseEntity<*>

    fun saveInfo(satelliteName: String, satellite: Satellite): ResponseEntity<*>

    fun getInfo(satelliteName: String): ResponseEntity<*>
}