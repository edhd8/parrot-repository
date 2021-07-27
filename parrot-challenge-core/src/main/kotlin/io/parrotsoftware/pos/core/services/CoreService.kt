package io.parrotsoftware.pos.core.services

import com.google.gson.Gson
import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.common.exceptions.ApiException
import io.parrotsoftware.pos.common.exceptions.ErrorCode
import io.parrotsoftware.pos.common.exceptions.ErrorMessage
import io.parrotsoftware.pos.domain.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class CoreService {
    private val logger = LoggerFactory.getLogger(CoreService::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    fun saveUser(userName: String, user: User): String {
        try {
            val gson = Gson()
            logger.info("--PARROT CHALLENGE --CoreService:saveUser --User Name: [{}] --User: {}", userName, gson.toJson(user))

            val userEntity = io.parrotsoftware.pos.domain.model.User().apply {
                email = user.email
                name = userName
                createdAt = Timestamp(System.currentTimeMillis())
            }

            val response = userRepository.save(userEntity)
            logger.info("--PARROT CHALLENGE --CoreService:saveUser --Response Save: [{}]", response)

            return HttpStatus.OK.name
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--PARROT CHALLENGE --CoreService:saveUser --Error: [{}]", e.message)
            throw ApiException(ErrorCode.SAVE_INFO, ErrorMessage.CONSTRAINT_SAVE_INFO_MESSAGE, HttpStatus.BAD_REQUEST)
        }
    }

}