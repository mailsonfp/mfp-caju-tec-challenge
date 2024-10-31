package com.mailson.pereira.caju.input.customer.dto

import com.mailson.pereira.caju.input.customer.dto.response.CustomerResponseInputDTO
import java.util.UUID

data class CustomerInputDTO (
    val id: Long? = null,
    val code: String? = UUID.randomUUID().toString(),
    val name: String
)

fun CustomerInputDTO.toResponse() = CustomerResponseInputDTO(
    code = this.code!!,
    name = this.name
)