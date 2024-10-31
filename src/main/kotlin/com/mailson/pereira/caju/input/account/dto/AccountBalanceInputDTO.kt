package com.mailson.pereira.caju.input.account.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mailson.pereira.caju.output.account.dto.AccountBalanceTypeEnum
import java.math.BigDecimal

data class AccountBalanceInputDTO(
    @JsonIgnore
    val id: Long? = null,
    @JsonIgnore
    val account: AccountInputDTO,
    val type: AccountBalanceTypeEnum,
    val balance: BigDecimal
)