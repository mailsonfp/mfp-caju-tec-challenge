package com.mailson.pereira.caju.input.account

import com.mailson.pereira.caju.input.account.dto.AccountBalanceMovementInputDTO

interface AccountBalanceMovementInput {
    fun save(accountBalanceMovementInputDTO: AccountBalanceMovementInputDTO)
}