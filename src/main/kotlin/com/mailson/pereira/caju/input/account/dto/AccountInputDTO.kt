package com.mailson.pereira.caju.input.account.dto

import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO

data class AccountInputDTO(
    val accountId: String,
    val customer: CustomerInputDTO,
    var accountStatus: String
)
