package io.parrotsoftware.pos.app.controllers

import io.parrotsoftware.pos.app.controllers.HealthCheckController
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.util.ReflectionTestUtils

@SpringBootTest
class HealthCheckControllerTest {

    @InjectMocks
    private lateinit var healthCheckController: HealthCheckController

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
        ReflectionTestUtils.setField(healthCheckController, "name", "Fuego-de-quasar")
    }

    @Test
    fun health() {
        val key = "[Fuego-de-quasar]"
        val value = "Status of the microservice is UP."
        val response = healthCheckController.getHealthCheck()
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(response.body?.get(key), value)
    }
}