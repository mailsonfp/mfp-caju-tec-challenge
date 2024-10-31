package com.mailson.pereira.caju.input.account.dto.response

import com.mailson.pereira.caju.input.account.dto.AccountBalanceInputDTO
import com.mailson.pereira.caju.input.account.dto.AccountInputDTO

data class AccountBalanceResponseDTO (
    val account: AccountInputDTO,
    val balances: List<AccountBalanceInputDTO>
)