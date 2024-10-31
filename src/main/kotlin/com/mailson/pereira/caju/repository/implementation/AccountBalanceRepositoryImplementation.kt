package com.mailson.pereira.caju.repository.implementation

import com.mailson.pereira.caju.output.account.AccountBalanceRepository
import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO
import com.mailson.pereira.caju.repository.domain.toEntity
import com.mailson.pereira.caju.repository.jpainterfaces.AccountBalanceJpaRepository
import org.springframework.stereotype.Repository

@Repository
class AccountBalanceRepositoryImplementation(
    private val accountBalanceJpaRepository: AccountBalanceJpaRepository
): AccountBalanceRepository {
    override fun save(accountBalanceOutputDTO: AccountBalanceOutputDTO) {
        accountBalanceJpaRepository.save(accountBalanceOutputDTO.toEntity())
    }
}