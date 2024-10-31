package com.mailson.pereira.caju.output.customer.dto

import jakarta.persistence.*

data class CustomerOutputDTO(
    val id: Long? = null,
    val code: String,
    val name: String
)
