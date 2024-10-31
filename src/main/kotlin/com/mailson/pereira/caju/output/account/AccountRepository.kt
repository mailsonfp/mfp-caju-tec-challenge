package com.mailson.pereira.caju.output.account

import com.mailson.pereira.caju.input.account.dto.AccountInputDTO
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO

interface AccountRepository {
    fun save(accountOutputDTO: AccountOutputDTO): AccountOutputDTO
    fun newAccount(accountOutputDTO: AccountOutputDTO): AccountOutputDTO
    fun findById(accountId: Long): AccountOutputDTO?
    fun findActiveAccountById(accountId: Long, accountStatus: String): AccountOutputDTO?
    fun findAccountsByCustomerCode(customerCode: String): List<AccountOutputDTO>
}