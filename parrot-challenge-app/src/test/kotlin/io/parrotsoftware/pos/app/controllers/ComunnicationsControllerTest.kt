package io.parrotsoftware.pos.app.controllers

import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.core.services.CoreService
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
    private lateinit var coreService: CoreService

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getLocation() {
        val userDto = User("edhd24@gmail.com")

        Mockito.`when`(coreService.saveUser("Edgar", userDto)).thenReturn(HttpStatus.OK.name)
        val response = comunnicationsController.saveUser("Edgar", userDto)
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
    }
}