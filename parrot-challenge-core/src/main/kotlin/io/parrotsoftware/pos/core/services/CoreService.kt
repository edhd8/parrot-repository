package io.parrotsoftware.pos.core.services

import com.google.gson.Gson
import io.parrotsoftware.pos.common.dto.Report
import io.parrotsoftware.pos.common.dto.User
import io.parrotsoftware.pos.common.exceptions.ApiException
import io.parrotsoftware.pos.common.exceptions.ErrorCode
import io.parrotsoftware.pos.common.exceptions.ErrorMessage
import io.parrotsoftware.pos.common.request.OrderRequest
import io.parrotsoftware.pos.common.responses.ReportResponse
import io.parrotsoftware.pos.domain.model.Order
import io.parrotsoftware.pos.domain.repository.OrderRepository
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

    @Autowired
    private lateinit var orderRepository: OrderRepository

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

    fun saveOrder(userName: String, order: OrderRequest): String {
        try {
            val gson = Gson()
            logger.info("--PARROT CHALLENGE --CoreService:saveOrder --User Name: [{}] --Order: {}", userName, gson.toJson(order))

            //TODO IFs Validar que el usuario y los productos existan y total price mayor a cero , 3 excepciones
            val orderEntity = io.parrotsoftware.pos.domain.model.Order().apply {
                name = userName
                products = gson.toJson(order.products)
                quantities = gson.toJson(order.quantities)
                subtotals = gson.toJson(order.subtotals)
                totalPrice = order.totalPrice
                createdAt = Timestamp(System.currentTimeMillis())
            }

            val response = orderRepository.save(orderEntity)
            logger.info("--PARROT CHALLENGE --CoreService:saveOrder --Response Save: [{}]", response)

            return HttpStatus.OK.name
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--PARROT CHALLENGE --CoreService:saveOrder --Error: [{}]", e.message)
            throw ApiException(ErrorCode.SAVE_INFO, ErrorMessage.DEFAULT_SAVE_INFO_MESSAGE, HttpStatus.NOT_FOUND)
        }
    }

    fun getReport(from: String, to: String): ReportResponse {
        try {
            logger.info("--PARROT CHALLENGE --CoreService:getReport --From: [{}] --To: [{}]", Timestamp.valueOf(from), to)

            var reportArray: ArrayList<Report> = arrayListOf()
            var reportResponse = ReportResponse(reportArray)

            val response = orderRepository.findBetween(Timestamp.valueOf(from), Timestamp.valueOf(to))
            logger.info("--PARROT CHALLENGE --CoreService:getReport --Response: [{}]", response)

            var listReport = buildListReport(response)

            reportResponse.apply {
                mostSelledProducts = listReport
            }

            return reportResponse
        } catch (e: Exception) {
            //TODO EXCEPTION LOCATION WITH I18N
            logger.error("--PARROT CHALLENGE --CoreService:getReport --Error: [{}]", e.message)
            throw ApiException(ErrorCode.SAVE_INFO, ErrorMessage.DEFAULT_SAVE_INFO_MESSAGE, HttpStatus.NOT_FOUND)
        }
    }

    private fun buildListReport(response: List<Order>?): List<Report> {
        var listReport: ArrayList<Report> = arrayListOf()
        if (response != null) {

            for (orderModel in response) {
                val report = Report(
                    orderModel.products,//TODO set most selled products in order descendent
                    1.0,//TODO set unit price
                    100.0//TODO set unit price*quantity
                )

                listReport.add(report)

            }

        }
        return listReport
    }

}