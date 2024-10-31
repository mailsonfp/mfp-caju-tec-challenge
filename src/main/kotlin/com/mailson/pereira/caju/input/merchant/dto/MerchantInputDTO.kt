package com.mailson.pereira.caju.input.merchant.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mailson.pereira.caju.input.account.dto.enums.AccountBalanceTypeEnum

data class MerchantInputDTO(
    @JsonIgnore
    val id: Long? = null,
    val merchantName: String,
    val principalMCC: AccountBalanceTypeEnum
)
