package com.mailson.pereira.caju.input.customer.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID

data class CustomerInputDTO (
    @JsonIgnore
    val id: Long? = null,
    val code: String? = UUID.randomUUID().toString(),
    val name: String
)