package com.mailson.pereira.caju.repository.implementation

import com.mailson.pereira.caju.output.account.AccountBalanceRepository
import com.mailson.pereira.caju.output.account.AccountRepository
import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO
import com.mailson.pereira.caju.output.account.dto.AccountBalanceTypeEnum
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import com.mailson.pereira.caju.repository.domain.toEntity
import com.mailson.pereira.caju.repository.domain.toOutputDTO
import com.mailson.pereira.caju.repository.jpainterfaces.AccountJpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import kotlin.jvm.optionals.getOrNull

@Repository
class AccountRepositoryImplementation(
    private val accountJpaRepository: AccountJpaRepository,
    private val accountBalanceRepository: AccountBalanceRepository
): AccountRepository {

    override fun save(accountOutputDTO: AccountOutputDTO): AccountOutputDTO {
        return accountJpaRepository.save(accountOutputDTO.toEntity()).toOutputDTO()
    }

    override fun newAccount(accountOutputDTO: AccountOutputDTO): AccountOutputDTO {
        // saving account
        val newAccount = save(accountOutputDTO)

        // initiate balance of all types with zero
        AccountBalanceTypeEnum.values().forEach {
            accountBalanceRepository.save(
                AccountBalanceOutputDTO(
                    account = newAccount,
                    balanceType = it.name,
                    balance = BigDecimal.ZERO
                )
            )
        }

        return newAccount
    }

    override fun findById(accountId: Long): AccountOutputDTO? {
        return accountJpaRepository.findById(accountId).getOrNull()?.toOutputDTO()
    }

    override fun findAccountByCustomerCode(customerCode: String, accountStatus: String): AccountOutputDTO? {
        return accountJpaRepository.findAccountsByCustomerCode(customerCode, accountStatus).getOrNull()?.toOutputDTO()
    }
}