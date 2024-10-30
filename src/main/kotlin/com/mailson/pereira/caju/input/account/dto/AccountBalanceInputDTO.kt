package com.mailson.pereira.caju.input.account.dto

import java.math.BigDecimal

data class AccountBalanceInputDTO(
    val balanceType: AccountInputDTO,
    val balance: BigDecimal
)
