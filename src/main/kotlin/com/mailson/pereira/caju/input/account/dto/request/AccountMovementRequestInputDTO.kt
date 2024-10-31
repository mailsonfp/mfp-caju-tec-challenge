package com.mailson.pereira.caju.input.account.dto.request

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class AccountMovementRequestInputDTO (
    @field:NotNull
    val movementAccountId: String,
    @field:NotNull
    val movementBalanceType: String,
    @field:NotNull
    val movementOperation: String,
    @field:NotNull
    val movementValue: BigDecimal
)
