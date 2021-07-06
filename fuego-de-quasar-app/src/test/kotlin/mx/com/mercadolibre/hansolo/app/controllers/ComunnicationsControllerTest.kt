package mx.com.mercadolibre.hansolo.app.controllers

import mx.com.mercadolibre.hansolo.common.responses.CoordinatesResponse
import mx.com.mercadolibre.hansolo.core.services.ResistanceService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class ComunnicationsControllerTest {

    @InjectMocks
    private lateinit var comunnicationsController: CommunicationsController

    @Mock
    private lateinit var resistanceService: ResistanceService

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getLocation() {
        val distances: DoubleArray = doubleArrayOf(100.0, 115.5, 142.7)
        val coordinatesResponse = CoordinatesResponse().apply{
            x = -58.3
            y = -69.6
        }
        Mockito.`when`(resistanceService.getLocation(distances)).thenReturn(coordinatesResponse)
        val response = comunnicationsController.getLocation(distances)
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
    }
}