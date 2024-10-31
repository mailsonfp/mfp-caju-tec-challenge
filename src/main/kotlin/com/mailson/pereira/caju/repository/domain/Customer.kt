package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import jakarta.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val code: String,

    val name: String
)

fun Customer.toOuputDTO() = CustomerOutputDTO(
    id = this.id,
    code = this.code,
    name = this.name
)

fun CustomerOutputDTO.toEntity() = Customer(
    id = this.id,
    code = this.code,
    name = this.name
)
