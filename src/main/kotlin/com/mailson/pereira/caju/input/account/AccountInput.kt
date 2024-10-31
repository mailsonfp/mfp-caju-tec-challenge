package com.mailson.pereira.caju.input.account

import com.mailson.pereira.caju.input.account.dto.AccountInputDTO
import com.mailson.pereira.caju.input.account.dto.enums.AccountStatusEnum
import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountBalanceResponseDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountResponseInputDTO

interface AccountInput {
    fun save(accountInputDTO: AccountInputDTO)
    fun newAccount(accountRequestInputDTO: AccountRequestInputDTO): AccountResponseInputDTO
    fun manageAccountStatus(accountId: String, accountStatus: AccountStatusEnum)
    fun findById(accountId: String): AccountInputDTO
    fun findActiveAccountById(accountId: String): AccountInputDTO
    fun findAccountsByCustomerCode(customerCode: String): List<AccountInputDTO>
    fun getAccountAndBalanceById(accountId: String): AccountBalanceResponseDTO
    fun movement(accountBalanceMovementRequestInputDTO: AccountMovementRequestInputDTO)
}