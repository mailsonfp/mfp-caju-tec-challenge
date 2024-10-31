package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.account.dto.AccountBalanceMovementOutputDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "account_balance_movement")
data class AccountBalanceMovement(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "account_balance_id")
    val accountBalance: AccountBalance,

    val timestamp: LocalDateTime?,
    val movementOperation: String,
    val movementValue: BigDecimal
)

fun AccountBalanceMovement.toOutputDTO() = AccountBalanceMovementOutputDTO(
    id = this.id,
    accountBalance = this.accountBalance.toOutputDTO(),
    timestamp = this.timestamp,
    movementOperation = this.movementOperation,
    movementValue = this.movementValue
)

fun AccountBalanceMovementOutputDTO.toEntity() = AccountBalanceMovement(
    id = this.id,
    accountBalance = this.accountBalance.toEntity(),
    timestamp = this.timestamp,
    movementOperation = this.movementOperation,
    movementValue = this.movementValue
)