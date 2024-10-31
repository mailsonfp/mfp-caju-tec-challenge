package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.account.AccountBalanceMovementInput
import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.account.dto.AccountBalanceInputDTO
import com.mailson.pereira.caju.input.account.dto.AccountBalanceMovementInputDTO
import com.mailson.pereira.caju.input.account.dto.AccountInputDTO
import com.mailson.pereira.caju.input.account.dto.enums.AccountMovementOperationEnum
import com.mailson.pereira.caju.input.account.dto.enums.AccountStatusEnum
import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountBalanceResponseDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountResponseInputDTO
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.input.exception.AccountNotFoundException
import com.mailson.pereira.caju.input.exception.InvalidMovementBalanceTypeException
import com.mailson.pereira.caju.input.exception.InvalidMovementValueException
import com.mailson.pereira.caju.output.account.AccountBalanceRepository
import com.mailson.pereira.caju.output.account.AccountRepository
import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO
import com.mailson.pereira.caju.output.account.dto.AccountBalanceTypeEnum
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val accountBalanceRepository: AccountBalanceRepository,
    private val accountBalanceMovementInput: AccountBalanceMovementInput,
    private val customerService: CustomerService
): AccountInput {

    private val loggerWriter = LoggerFactory.getLogger(this::class.java)

    override fun save(accountInputDTO: AccountInputDTO) {
        loggerWriter.info("method=save, status=init, request=$accountInputDTO")
        accountRepository.save(toAccountOutputDTO(accountInputDTO))
    }

    override fun newAccount(accountRequestInputDTO: AccountRequestInputDTO): AccountResponseInputDTO {
        loggerWriter.info("method=newAccount, status=init, request=$accountRequestInputDTO")

        loggerWriter.info("method=newAccount, status=findCustomerByCode")
        val customer = customerService.findByCode(accountRequestInputDTO.customerCode)

        loggerWriter.info("method=newAccount, status=saveNewAccount")
        val newAccount = accountRepository.newAccount(toAccountOutputDTO(customer, AccountStatusEnum.ACTIVE))

        return AccountResponseInputDTO(
            accountId = newAccount.id.toString(),
            customerCode = newAccount.customer.code
        )
    }

    override fun manageAccountStatus(accountId: String, newAccountStatus: AccountStatusEnum) {
        val accountInputDTO = findById(accountId)
        accountInputDTO.accountStatus = newAccountStatus.name
        save(accountInputDTO)
    }

    override fun findById(accountId: String): AccountInputDTO {
        val account = accountRepository.findById(accountId.toLong())
            .takeIf { it != null } ?: throw AccountNotFoundException("Unable to find active account with id=$accountId")

        return toAccountInputDTO(account)
    }

    override fun findActiveAccountById(accountId: String): AccountInputDTO {
        val account = accountRepository.findActiveAccountById(accountId.toLong(), AccountStatusEnum.ACTIVE.name)
            .takeIf { it != null } ?: throw AccountNotFoundException("Unable to find active account with id=$accountId")

        return toAccountInputDTO(account)
    }

    override fun findAccountsByCustomerCode(customerCode: String): List<AccountInputDTO> {
        val customer = customerService.findByCode(customerCode)
        val listAccount = accountRepository.findAccountsByCustomerCode(customer.code!!)
        return if (listAccount.isNotEmpty()) return listAccount.map { toAccountInputDTO(it) } else throw AccountNotFoundException("Customer with code $customerCode have no accounts ")
    }

    override fun getAccountAndBalanceById(accountId: String): AccountBalanceResponseDTO {
        val account = findById(accountId)
        val accountBalanceList = accountBalanceRepository.getFindBalancesByAccountId(account.accountId.toLong())

        val listBalanceInputDTO = arrayListOf<AccountBalanceInputDTO>()
        accountBalanceList.forEach {
            listBalanceInputDTO.add(AccountBalanceInputDTO(
                id = it.id,
                account = toAccountInputDTO(it.account),
                type = AccountBalanceTypeEnum.valueOf(it.balanceType),
                balance = it.balance!!
            ))
        }

        return AccountBalanceResponseDTO(
            account = account,
            balances = listBalanceInputDTO
        )
    }

    override fun movement(accountBalanceMovementRequestInputDTO: AccountMovementRequestInputDTO) {
        loggerWriter.info("method=movement, status=init, request=$accountBalanceMovementRequestInputDTO")

        loggerWriter.info("method=movement, status=findAccountById")
        val account = findById(accountBalanceMovementRequestInputDTO.movementAccountId)

        var message = ""

        loggerWriter.info("method=movement, status=validatBalanceTypeAndOperationType")
        lateinit var movementBalanceType: AccountBalanceTypeEnum
        lateinit var movementOperation: AccountMovementOperationEnum
        try {
            message = "${accountBalanceMovementRequestInputDTO.movementBalanceType} is not a valid movement balance type"
            movementBalanceType = AccountBalanceTypeEnum.valueOf(accountBalanceMovementRequestInputDTO.movementBalanceType)

            message = "${accountBalanceMovementRequestInputDTO.movementOperation} is not a valid movement operation type"
            movementOperation = AccountMovementOperationEnum.valueOf(accountBalanceMovementRequestInputDTO.movementOperation)
        } catch (ex: IllegalArgumentException){
            throw InvalidMovementBalanceTypeException(message)
        }

        loggerWriter.info("method=movement, status=getBalanceByAccountIdAndType")
        val accountBalanceOutput = accountBalanceRepository.getBalanceByAccountIdAndType(account.accountId.toLong(), movementBalanceType.name)

        loggerWriter.info("method=movement, status=validateMovementValue")
        val movementValue = accountBalanceMovementRequestInputDTO.movementValue.takeIf { it > BigDecimal.ZERO } ?: throw InvalidMovementValueException("Movement value must b greater than zero")

        loggerWriter.info("method=movement, status=saveMovement")
        // cria o lançamento na conta referente ao tipo de balance
        accountBalanceMovementInput.save(toAccountBalanceMovementInputDTO(
            accountBalanceOutput,
            movementBalanceType,
            movementOperation,
            movementValue
        ))

        loggerWriter.info("method=movement, status=updateBalance")
        // rotina para atualizar o saldo
        val newBalance = if (movementOperation == AccountMovementOperationEnum.DEBIT)
            accountBalanceOutput.balance!!.minus(movementValue)
        else accountBalanceOutput.balance!!.plus(movementValue)

        accountBalanceOutput.balance = newBalance
        accountBalanceRepository.save(accountBalanceOutput)
    }

    fun toAccountInputDTO(accountOutputDTO: AccountOutputDTO): AccountInputDTO {
        return AccountInputDTO(
            accountId = accountOutputDTO.id.toString(),
            customer = CustomerInputDTO(
                id = accountOutputDTO.customer.id,
                name = accountOutputDTO.customer.name,
                code = accountOutputDTO.customer.code!!
            ),
            accountStatus = accountOutputDTO.accountStatus
        )
    }

    private fun toAccountOutputDTO(accountInputDTO: AccountInputDTO): AccountOutputDTO{
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

    private fun toAccountBalanceMovementInputDTO(
        accountBalanceOutputDTO: AccountBalanceOutputDTO,
        movementBalanceType: AccountBalanceTypeEnum,
        movementOperation: AccountMovementOperationEnum,
        movementValue: BigDecimal
    ): AccountBalanceMovementInputDTO{
        return AccountBalanceMovementInputDTO(
            accountBalance = AccountBalanceInputDTO(
                id = accountBalanceOutputDTO.id,
                account = toAccountInputDTO(accountBalanceOutputDTO.account),
                balance = accountBalanceOutputDTO.balance!!,
                type = movementBalanceType
            ),
            movementOperation = movementOperation,
            movementValue = movementValue
        )
    }
}