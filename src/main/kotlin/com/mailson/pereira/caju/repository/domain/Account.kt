package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.account.dto.AccountOutputDTO
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "account")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    var accountStatus: String
)

fun Account.toOutputDTO() = AccountOutputDTO(
    id = this.id,
    customer = CustomerOutputDTO(
        id = this.customer.id,
        code = this.customer.code,
        name = this.customer.name),
    accountStatus = this.accountStatus
)

fun AccountOutputDTO.toEntity() = Account(
    id = this.id,
    customer = Customer(
        id = this.customer.id,
        code = this.customer.code,
        name = this.customer.name),
    accountStatus = this.accountStatus
)