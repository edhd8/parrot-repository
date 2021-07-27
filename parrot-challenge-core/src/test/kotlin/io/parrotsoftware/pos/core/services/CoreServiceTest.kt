package io.parrotsoftware.pos.core.services

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.domain.repository.UserRepository
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
class CoreServiceTest {
    @InjectMocks
    private lateinit var coreService: CoreService

    @Mock
    private lateinit var userRepository: UserRepository

    val userDto = User("edhd24@gmail.com")

    @Before
    fun prepare() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `saveUser unique you cannot update`() {
        val user = io.parrotsoftware.pos.domain.model.User().apply {
            email = "edhd24@gmail.com"
        }
        Mockito.`when`(userRepository.save(Mockito.any(io.parrotsoftware.pos.domain.model.User::class.java)))
            .thenReturn(user)
        coreService.saveUser("Edgar Guerrero", userDto)
        verify(userRepository, times(1)).save(Mockito.any(io.parrotsoftware.pos.domain.model.User::class.java))
    }

}