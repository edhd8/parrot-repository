package io.parrotsoftware.pos.common.exceptions

import org.springframework.http.HttpStatus

class ApiException(

    val code: String,
    override val message: String,
    val status: HttpStatus

) : RuntimeException()