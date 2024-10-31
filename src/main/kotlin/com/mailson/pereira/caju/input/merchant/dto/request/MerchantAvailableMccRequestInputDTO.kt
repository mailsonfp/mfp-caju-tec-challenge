package com.mailson.pereira.caju.input.merchant.dto.request

import jakarta.validation.constraints.NotBlank

data class MerchantAvailableMccRequestInputDTO (
    @field:NotBlank(message = "must not be blank")
    val merchantName: String,
    @field:NotBlank(message = "must not be blank")
    val mccCode: String
)