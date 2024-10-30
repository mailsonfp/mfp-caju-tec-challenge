package com.mailson.pereira.caju.input.account.dto

import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO

data class AccountInputDTO(
    val customer: CustomerInputDTO,
    val accountId: String,
    val balances: List<AccountBalanceInputDTO>
)