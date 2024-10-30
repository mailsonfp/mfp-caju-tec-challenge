package com.mailson.pereira.caju.input.customer.dto

import java.util.UUID

data class CustomerInputDTO (
    val id: Long,
    val name: String,
    val code: String? = UUID.randomUUID().toString()
)