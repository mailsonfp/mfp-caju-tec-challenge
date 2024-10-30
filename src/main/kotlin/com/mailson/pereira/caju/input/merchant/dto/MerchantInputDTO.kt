package com.mailson.pereira.caju.input.merchant.dto

import com.mailson.pereira.caju.input.account.dto.enums.AccountBalanceTypeEnum

data class MerchantInputDTO(
    val merchantName: String,
    val principalMCC: AccountBalanceTypeEnum,
    val availableMCCs: List<String>
)
