package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.AccountBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AccountBalanceJpaRepository : JpaRepository<AccountBalance,Long>{

    @Query("FROM AccountBalance accb where accb.account.id = :accountId")
    fun getBalancesByAccountId(accountId: Long): List<AccountBalance>

    @Query("FROM AccountBalance accb where accb.account.id = :accountId and accb.balanceType =  :balanceType")
    fun getBalanceByAccountIdAndType(accountId: Long, balanceType: String): AccountBalance
}