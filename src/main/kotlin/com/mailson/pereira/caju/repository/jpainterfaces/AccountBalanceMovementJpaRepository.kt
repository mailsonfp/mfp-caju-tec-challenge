package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.AccountBalanceMovement
import org.springframework.data.jpa.repository.JpaRepository

interface AccountBalanceMovementJpaRepository : JpaRepository<AccountBalanceMovement,Long>