package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.merchant.dto.MerchantOutputDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "merchant")
data class Merchant(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val merchantName: String,
    val principalMCC: String
)

fun Merchant.toOutputDTO() = MerchantOutputDTO(
    id = this.id,
    merchantName = this.merchantName,
    principalMCC = this.principalMCC
)

fun MerchantOutputDTO.toEntity() = Merchant(
    id = this.id,
    merchantName = this.merchantName,
    principalMCC = this.principalMCC
)
