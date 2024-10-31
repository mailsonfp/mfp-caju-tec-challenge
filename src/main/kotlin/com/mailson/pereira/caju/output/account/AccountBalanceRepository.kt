package com.mailson.pereira.caju.output.account

import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO

interface AccountBalanceRepository {

    fun save(accountBalanceOutputDTO: AccountBalanceOutputDTO)
}