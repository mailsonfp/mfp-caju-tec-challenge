package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.account.AccountBalanceMovementInput
import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.account.dto.AccountBalanceInputDTO
import com.mailson.pereira.caju.input.account.dto.enums.AccountBalanceTypeEnum
import com.mailson.pereira.caju.input.account.dto.enums.AccountMovementOperationEnum
import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.exception.AccountNotFoundException
import com.mailson.pereira.caju.input.exception.InsuficientBalanceException
import com.mailson.pereira.caju.input.exception.MerchantNotFoundException
import com.mailson.pereira.caju.input.exception.TransactionAuthorizeProcessException
import com.mailson.pereira.caju.input.merchant.MerchantInput
import com.mailson.pereira.caju.input.merchant.dto.MerchantInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantAvailableMccRequestInputDTO
import com.mailson.pereira.caju.input.transaction.TransactionInput
import com.mailson.pereira.caju.input.transaction.dto.enums.TransactionMccCodeEnum
import com.mailson.pereira.caju.input.transaction.dto.enums.TransactionResponseCodeEnum
import com.mailson.pereira.caju.input.transaction.dto.request.TransactionInputRequestDTO
import com.mailson.pereira.caju.input.transaction.dto.response.TransactionResponseDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TransactionService(
    private val accountInput: AccountInput,
    private val merchantInput: MerchantInput
): TransactionInput {

    private val loggerWriter = LoggerFactory.getLogger(this::class.java)

    override fun authorize(transactionRequest: TransactionInputRequestDTO): TransactionResponseDTO {
        loggerWriter.info("method=authorize, status=init, request=$transactionRequest")
        try {
            val merchant = merchantInput.findByMerchantName(transactionRequest.merchant)
            val account = accountInput.getAccountAndBalanceById(transactionRequest.account)
            val accountBalanceType = getAccountBalanceType(merchant, transactionRequest.mcc)
            val accountBalanceToProcess = getAccountBalanceToProcess(
                account.balances,
                accountBalanceType,
                transactionRequest.totalAmount
            )

            if (transactionRequest.totalAmount <= accountBalanceToProcess.balance){
                val accountMovementRequest = AccountMovementRequestInputDTO(
                    movementAccountId = account.account.accountId,
                    movementBalanceType = accountBalanceToProcess.type.name,
                    movementOperation = AccountMovementOperationEnum.DEBIT.name,
                    movementValue = transactionRequest.totalAmount
                )

                accountInput.movement(accountMovementRequest)
            } else throw InsuficientBalanceException("Account with insuficient balance to authorize transaction")
        } catch (ex: MerchantNotFoundException){
            loggerWriter.error("method=authorize, status=error, message=${ex.message}")
            throw TransactionAuthorizeProcessException(TransactionResponseCodeEnum.ERROR.responseCode)
        } catch (ex:InsuficientBalanceException){
            loggerWriter.error("method=authorize, status=error, message=${ex.message}")
            throw TransactionAuthorizeProcessException(TransactionResponseCodeEnum.DECLINED.responseCode)
        } catch (ex: AccountNotFoundException) {
            loggerWriter.error("method=authorize, status=error, message=${ex.message}")
            throw TransactionAuthorizeProcessException(TransactionResponseCodeEnum.ERROR.responseCode)
        }catch (ex:Exception){
            loggerWriter.error("method=authorize, status=error, message=${ex.message}")
            throw TransactionAuthorizeProcessException(TransactionResponseCodeEnum.ERROR.responseCode)
        }

        return TransactionResponseDTO(code = TransactionResponseCodeEnum.AUTHORIZED.responseCode )
    }

    private fun getAccountBalanceType(merchant: MerchantInputDTO, mccCode: String): AccountBalanceTypeEnum{
        val merchantMccCode = merchantInput.findExistAvailableMccCode(merchant.merchantName, mccCode)
        val mccCodeFilter = merchantMccCode?.code ?: mccCode
        return TransactionMccCodeEnum.getAccountBalanceTypeEnum(mccCodeFilter)
    }

    private fun getAccountBalanceToProcess(accountBalances: List<AccountBalanceInputDTO>, accountBalanceType: AccountBalanceTypeEnum, totalAmount: BigDecimal): AccountBalanceInputDTO{
        var accountBalanceToProcess = accountBalances.firstOrNull{ it.type.name == accountBalanceType.name }
            ?: throw Exception("Unable to find balance for account")

        if (totalAmount > accountBalanceToProcess.balance && accountBalanceType != AccountBalanceTypeEnum.CASH)
            accountBalanceToProcess = accountBalances.first{ it.type.name == TransactionMccCodeEnum.MCCDEFAULT.accountBalanceTypeEnum.name }

        return  accountBalanceToProcess
    }
}