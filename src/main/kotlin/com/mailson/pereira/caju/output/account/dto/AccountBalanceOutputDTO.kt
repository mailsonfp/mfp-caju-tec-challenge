package com.mailson.pereira.caju.output.account.dto

import java.math.BigDecimal

data class AccountBalanceOutputDTO (
    val id: Long? = null,
    val account: AccountOutputDTO,
    val balanceType: String,
    var balance: BigDecimal? = BigDecimal.ZERO
)