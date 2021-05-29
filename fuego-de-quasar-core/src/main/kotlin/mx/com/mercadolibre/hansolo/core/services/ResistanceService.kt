package mx.com.mercadolibre.hansolo.core.services

import com.google.gson.Gson
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver
import com.lemmingapex.trilateration.TrilaterationFunction
import mx.com.mercadolibre.hansolo.common.dto.Satellite
import mx.com.mercadolibre.hansolo.common.exceptions.ApiException
import mx.com.mercadolibre.hansolo.common.exceptions.ErrorCode
import mx.com.mercadolibre.hansolo.common.exceptions.ErrorMessage
import mx.com.mercadolibre.hansolo.common.request.Satellites
import mx.com.mercadolibre.hansolo.common.responses.CoordinatesResponse
import mx.com.mercadolibre.hansolo.common.responses.InfoResponse
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Arrays
import java.util.Collections
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class ResistanceService {
    private val logger = LoggerFactory.getLogger(ResistanceService::class.java)

    fun getLocation(distances: DoubleArray) : CoordinatesResponse {
        try {
            var coordinates = CoordinatesResponse()
            logger.info("--FUEGO DE QUASAR --ResistanceService:getLocation --Distance: [{}]", distances)

            //satellite positions.
            val positions =
                arrayOf(doubleArrayOf(-500.0, -200.0), doubleArrayOf(100.0, -100.0), doubleArrayOf(500.0, 100.0))

            //Solves a formulation of n-D space trilateration problem using a nonlinear least squares optimizer.
            val solver = NonLinearLeastSquaresSolver(
                TrilaterationFunction(positions, distances),
                LevenbergMarquardtOptimizer()
            )

            val optimum = solver.solve()

            val centroid = optimum.point.toArray()
            logger.info("--FUEGO DE QUASAR --ResistanceService:getLocation --Centroid: [{}]", centroid)

            coordinates.apply {
                x = String.format("%.1f", centroid.get(0)).toDouble()
                y = String.format("%.1f", centroid.get(1)).toDouble()
            }

            return coordinates
        } catch (e: NoSuchElementException) {
            logger.error("--FUEGO DE QUASAR --ResistanceService:getLocation --Error: [{}]", e.message)
            return CoordinatesResponse()
            //TO DO EXCEPTION LOCATION WITH I18N throw LocationException
        }
    }

    fun getMessage(satellites: Satellites): String {
        try {
            val gson = Gson()
            logger.info("--FUEGO DE QUASAR --ResistanceService:getMessage --Satellites: {}", gson.toJson(satellites))

            val words = findWords(satellites)
            logger.info("--FUEGO DE QUASAR --ResistanceService:getMessage --Words: {}", words)

            return if(validateSatellitesMessagesSize(satellites, words.size)){
                val satellitesMessages = cleanSatellitesMessages(satellites, words.size)
                logger.info("--FUEGO DE QUASAR --ResistanceService:getMessage --SatellitesMessages: {}", gson.toJson(satellitesMessages))
                assembleMessage(satellitesMessages)
            }else{
                //TODO IF IS VALID FALSE EXCEPTION TAMAÑO INVÁLIDO
                logger.info("--FUEGO DE QUASAR --ResistanceService:getMessage --SatellitesMessages FALSE: {}")
                throw ApiException(ErrorCode.INVALID_SIZE, ErrorMessage.INVALID_SIZE, HttpStatus.NOT_FOUND)
            }
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--FUEGO DE QUASAR --ResistanceService:getMessage --Error: [{}]", e.message)
            throw ApiException(ErrorCode.GET_MESSAGE, ErrorMessage.DEFAULT_MESSAGE, HttpStatus.NOT_FOUND)
        }
    }

    fun calculateInfo(satellites: Satellites): InfoResponse {
        try {
            val gson = Gson()
            logger.info("--FUEGO DE QUASAR --ResistanceService:calculateInfo --Satellites: {}", gson.toJson(satellites))

            val distances: DoubleArray = getDistances(satellites)

            val infoResponse = InfoResponse().apply{
                position = getLocation(distances)
                message = getMessage(satellites)
            }

            return infoResponse
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--FUEGO DE QUASAR --ResistanceService:calculateInfo --Error: [{}]", e.message)
            throw ApiException(ErrorCode.CALCULATE_INFO, ErrorMessage.DEFAULT_CALCULATE_INFO_MESSAGE, HttpStatus.NOT_FOUND)
            //throw InfoResponse
        }
    }

    fun saveInfo(satelliteName: String, satellite: Satellite): String {
        try {
            val gson = Gson()
            logger.info("--FUEGO DE QUASAR --ResistanceService:saveInfo --Satellite Name: [{}] --Satellite: {}", satelliteName, gson.toJson(satellite))

            return HttpStatus.OK.name
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--FUEGO DE QUASAR --ResistanceService:saveInfo --Error: [{}]", e.message)
            throw ApiException(ErrorCode.SAVE_INFO, ErrorMessage.DEFAULT_SAVE_INFO_MESSAGE, HttpStatus.NOT_FOUND)
        }
    }

    fun getInfo(satelliteName: String): InfoResponse {
        try {
            logger.info("--FUEGO DE QUASAR --ResistanceService:getInfo --Satellite Name: [{}]", satelliteName)

            return InfoResponse()
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--FUEGO DE QUASAR --ResistanceService:getInfo --Error: [{}]", e.message)
            throw ApiException(ErrorCode.GET_INFO, ErrorMessage.DEFAULT_GET_INFO_MESSAGE, HttpStatus.NOT_FOUND)
        }
    }

    fun findWords(satellites: Satellites): List<String> {
        var listWords: MutableList<String> = ArrayList()
        for (satellite in satellites.satellites) {
            listWords = Stream.concat(listWords.stream(), satellite.message.stream())
                .distinct()
                .collect(Collectors.toList())
        }
        //removing gap
        listWords.remove("")
        return listWords
    }

    fun cleanSatellitesMessages(satellites: Satellites, totalWords: Int): ArrayList<ArrayList<String>> {
        val satellitesMessages: ArrayList<ArrayList<String>> = arrayListOf()

        for (satellite in satellites.satellites) {
            satellitesMessages.add(satellite.message)
        }

        return satellitesMessages
    }

    private fun assembleMessage(satellitesMessages: List<java.util.ArrayList<String>>): String {
        var finalMessage = ""
        for (message in satellitesMessages) {
            if (message.size > 0 && message[0] != "") {
                finalMessage = if (message.size == 1) message[0] else message[0] + " "
                satellitesMessages.forEach { s: java.util.ArrayList<String> ->
                    s.removeAt(0)
                }
                return finalMessage + assembleMessage(satellitesMessages)
            }
        }

        return ""
    }

    private fun validateSatellitesMessagesSize(satellites: Satellites, totalWords: Int): Boolean {
        for (satellite in satellites.satellites) {
            if (satellite.message.size > totalWords) {
                return false
            }
        }
        return true
    }

    fun getDistances(satellites: Satellites): DoubleArray {
        try {
            var doubleArray: ArrayList<Double> = arrayListOf()

            for (satellite in satellites.satellites) {
                doubleArray.add(satellite.distance)
            }

            return doubleArray.toDoubleArray()
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--FUEGO DE QUASAR --ResistanceService:getDistances --Error: [{}]", e.message)
            throw ApiException(ErrorCode.GET_DISTANCES, ErrorMessage.GET_DISTANCES, HttpStatus.NOT_FOUND)
        }
    }
}