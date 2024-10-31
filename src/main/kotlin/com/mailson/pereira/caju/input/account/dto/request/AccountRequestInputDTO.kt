package com.mailson.pereira.caju.input.account.dto.request

import jakarta.validation.constraints.NotBlank

data  class AccountRequestInputDTO (
    @field:NotBlank(message = "Customer code must not be blank")
    val customerCode: String
)