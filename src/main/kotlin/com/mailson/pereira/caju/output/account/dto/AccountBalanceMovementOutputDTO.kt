package com.mailson.pereira.caju.output.account.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountBalanceMovementOutputDTO (
    val id: Long? = null,
    val accountBalance: AccountBalanceOutputDTO,
    val timestamp: LocalDateTime? = LocalDateTime.now(),
    val movementOperation: String,
    val movementValue: BigDecimal
)