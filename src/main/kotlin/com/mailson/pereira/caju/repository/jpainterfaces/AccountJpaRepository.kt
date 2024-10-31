package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface AccountJpaRepository : JpaRepository<Account, Long>{

    @Query("FROM Account acc where acc.customer.code = :customerCode and acc.accountStatus = :accountStatus")
    fun findAccountsByCustomerCode(customerCode: String, accountStatus: String): Optional<Account>
}