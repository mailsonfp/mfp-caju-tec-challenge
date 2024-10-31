package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.account.dto.AccountBalanceOutputDTO
import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "account_balance")
data class AccountBalance (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    val balanceType: String,

    val balance: BigDecimal?
)

fun AccountBalance.toOutputDTO() = AccountBalanceOutputDTO(
    id = this.id,
    account = AccountOutputDTO(
        id = this.account.id,
        customer = this.account.customer.toOuputDTO(),
        accountStatus = this.account.accountStatus
    ),
    balanceType = this.balanceType,
    balance = this.balance
)

fun AccountBalanceOutputDTO.toEntity() = AccountBalance(
    id = this.id,
    account = Account(
        id = this.account.id,
        customer = this.account.customer.toEntity(),
        accountStatus = this.account.accountStatus
    ),
    balanceType = this.balanceType,
    balance = this.balance
)