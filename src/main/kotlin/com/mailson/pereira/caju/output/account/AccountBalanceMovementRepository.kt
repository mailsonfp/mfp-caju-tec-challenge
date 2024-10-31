package com.mailson.pereira.caju.output.account

import com.mailson.pereira.caju.output.account.dto.AccountBalanceMovementOutputDTO

interface AccountBalanceMovementRepository {
    fun save(accountBalanceMovementOutputDTO: AccountBalanceMovementOutputDTO)
}