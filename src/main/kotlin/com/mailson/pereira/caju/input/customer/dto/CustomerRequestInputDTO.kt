package com.mailson.pereira.caju.input.customer.dto

import jakarta.validation.constraints.NotBlank

data class CustomerRequestInputDTO(
    @field:NotBlank(message = "Must not be blank")
    val name: String
)
