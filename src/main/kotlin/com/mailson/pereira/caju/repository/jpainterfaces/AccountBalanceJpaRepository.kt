package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.AccountBalance
import org.springframework.data.jpa.repository.JpaRepository

interface AccountBalanceJpaRepository : JpaRepository<AccountBalance,Long>