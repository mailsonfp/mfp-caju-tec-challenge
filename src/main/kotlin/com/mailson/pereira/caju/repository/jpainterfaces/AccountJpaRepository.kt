package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface AccountJpaRepository : JpaRepository<Account, Long>{

    fun findByIdAndAccountStatus(id: Long, accountStatus: String): Optional<Account>

    @Query("FROM Account ac where ac.customer.code = :customerCode")
    fun findAccountsByCustomerCode(customerCode: String): List<Account>
}