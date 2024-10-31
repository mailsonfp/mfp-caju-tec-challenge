package com.mailson.pereira.caju.input.account.dto

import com.mailson.pereira.caju.input.account.dto.enums.AccountMovementOperationEnum
import com.mailson.pereira.caju.output.account.dto.AccountBalanceTypeEnum
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountBalanceMovementInputDTO(
    val id: Long? = null,
    val accountBalance: AccountBalanceInputDTO,
    val timestamp: LocalDateTime? = LocalDateTime.now(),
    val movementOperation: AccountMovementOperationEnum,
    val movementValue: BigDecimal
)
