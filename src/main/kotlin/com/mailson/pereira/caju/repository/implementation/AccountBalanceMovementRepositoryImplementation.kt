package com.mailson.pereira.caju.repository.implementation

import com.mailson.pereira.caju.output.account.AccountBalanceMovementRepository
import com.mailson.pereira.caju.output.account.dto.AccountBalanceMovementOutputDTO
import com.mailson.pereira.caju.repository.domain.toEntity
import com.mailson.pereira.caju.repository.jpainterfaces.AccountBalanceMovementJpaRepository
import org.springframework.stereotype.Repository

@Repository
class AccountBalanceMovementRepositoryImplementation(
    private val accountBalanceMovementJpaRepository: AccountBalanceMovementJpaRepository
): AccountBalanceMovementRepository {

    override fun save(accountBalanceMovementOutputDTO: AccountBalanceMovementOutputDTO) {
        accountBalanceMovementJpaRepository.save(accountBalanceMovementOutputDTO.toEntity())
    }
}