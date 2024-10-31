package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.account.dto.enums.AccountStatusEnum
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountResponseInputDTO
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.output.account.AccountRepository
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val customerService: CustomerService
): AccountInput {

    override fun newAccount(accountRequestInputDTO: AccountRequestInputDTO): AccountResponseInputDTO {
        val customer = customerService.findByCode(accountRequestInputDTO.customerCode)
        val newAccount = accountRepository.newAccount(toAccountOutputDTO(customer, AccountStatusEnum.ACTIVE))

        return AccountResponseInputDTO(
            accountId = newAccount.id.toString(),
            customerCode = newAccount.customer.code
        )
    }

    private fun toAccountOutputDTO(customer: CustomerInputDTO, accountStatus: AccountStatusEnum): AccountOutputDTO{
        return AccountOutputDTO(
            customer = CustomerOutputDTO(
                id = customer.id,
                name = customer.name,
                code = customer.code!!
            ),
            accountStatus = accountStatus.name
        )
    }
}