package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.account.AccountBalanceMovementInput
import com.mailson.pereira.caju.input.account.dto.AccountBalanceMovementInputDTO
import com.mailson.pereira.caju.input.account.dto.AccountInputDTO
import com.mailson.pereira.caju.output.account.AccountBalanceMovementRepository
import com.mailson.pereira.caju.output.account.dto.AccountBalanceMovementOutputDTO
import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import org.springframework.stereotype.Service

@Service
class AccountBalanceMovementService(
    private val accountBalanceMovementRepository: AccountBalanceMovementRepository
): AccountBalanceMovementInput {
    override fun save(accountBalanceMovementInputDTO: AccountBalanceMovementInputDTO) {
        accountBalanceMovementRepository.save(toAccountBalanceMovementOutputDTO(accountBalanceMovementInputDTO))
    }

    fun toAccountBalanceMovementOutputDTO(accountBalanceMovementInputDTO: AccountBalanceMovementInputDTO): AccountBalanceMovementOutputDTO{
        return AccountBalanceMovementOutputDTO(
            id = accountBalanceMovementInputDTO.id,
            accountBalance = AccountBalanceOutputDTO(
                id = accountBalanceMovementInputDTO.accountBalance.id,
                account = toAccountOutputDTO(accountBalanceMovementInputDTO.accountBalance.account),
                balanceType = accountBalanceMovementInputDTO.accountBalance.type.name
            ),
            movementOperation = accountBalanceMovementInputDTO.movementOperation.name,
            movementValue = accountBalanceMovementInputDTO.movementValue
        )
    }
    private fun toAccountOutputDTO(accountInputDTO: AccountInputDTO): AccountOutputDTO {
        return AccountOutputDTO(
            id = accountInputDTO.accountId.toLong(),
            customer = CustomerOutputDTO(
                id = accountInputDTO.customer.id,
                name = accountInputDTO.customer.name,
                code = accountInputDTO.customer.code!!
            ),
            accountStatus = accountInputDTO.accountStatus
        )
    }
}