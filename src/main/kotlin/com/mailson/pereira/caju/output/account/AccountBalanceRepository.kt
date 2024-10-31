package com.mailson.pereira.caju.output.account

import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO

interface AccountBalanceRepository {

    fun save(accountBalanceOutputDTO: AccountBalanceOutputDTO)
    fun getFindBalancesByAccountId(accountId: Long): List<AccountBalanceOutputDTO>
    fun getBalanceByAccountIdAndType(accountId: Long, balanceType: String): AccountBalanceOutputDTO
}