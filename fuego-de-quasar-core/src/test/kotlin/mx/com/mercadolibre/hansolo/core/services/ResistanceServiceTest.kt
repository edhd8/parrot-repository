package mx.com.mercadolibre.hansolo.core.services

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import mx.com.mercadolibre.hansolo.common.dto.Satellite
import mx.com.mercadolibre.hansolo.common.request.Satellites
import mx.com.mercadolibre.hansolo.common.responses.CoordinatesResponse
import mx.com.mercadolibre.hansolo.common.responses.InfoResponse
import mx.com.mercadolibre.hansolo.domain.repository.SatelliteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest

@SpringBootConfiguration
@SpringBootTest
class ResistanceServiceTest {
    @InjectMocks
    private lateinit var resistanceService: ResistanceService

    @Mock
    private lateinit var satelliteRepository: SatelliteRepository

    val satellitesList: List<Satellite> = listOf(
        Satellite("kenobi", 100.0, arrayListOf("este","","","mensaje","")),
        Satellite("skywalker", 115.5, arrayListOf("","es","","","secreto")),
        Satellite("sato", 142.7, arrayListOf("este","","un","",""))
    )

    val satellites = Satellites(satellitesList)

    val satelliteDto = Satellite("kenobi", 100.0, arrayListOf("este","","","mensaje",""))

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `getLocation`() {
        val distances: DoubleArray = doubleArrayOf(100.0, 115.5, 142.7)
        val response = resistanceService.getLocation(distances)
        Assert.assertNotNull(response)
    }

    @Test
    fun `getMessage`() {
        val response = resistanceService.getMessage(satellites)
        Assert.assertEquals("este es un mensaje secreto", response)
    }

    @Test
    fun `calculateInfo`() {
        val infoResponse = InfoResponse().apply{
            position = CoordinatesResponse(-58.3, -69.6)
            message = "este es un mensaje secreto"
        }
        val response = resistanceService.calculateInfo(satellites)
        Assert.assertEquals(infoResponse, response)
    }

    @Test
    fun `saveInfo`() {
        val satellite=mx.com.mercadolibre.hansolo.domain.model.Satellite().apply {
            id=1
        }
        Mockito.`when`(satelliteRepository.save(Mockito.any(mx.com.mercadolibre.hansolo.domain.model.Satellite::class.java))).thenReturn(satellite)
        resistanceService.saveInfo("sato", satelliteDto)
        verify(satelliteRepository, times(1)).save(Mockito.any(mx.com.mercadolibre.hansolo.domain.model.Satellite::class.java))
    }

}