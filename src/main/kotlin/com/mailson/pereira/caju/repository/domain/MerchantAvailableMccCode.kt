package com.mailson.pereira.caju.repository.domain

import com.mailson.pereira.caju.output.merchant.dto.MerchantAvailableMccCodeOutputDTO
import com.mailson.pereira.caju.output.merchant.dto.MerchantOutputDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "merchant_available_cc_code")
data class MerchantAvailableMccCode (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    val merchant: Merchant,

    val code: String
)

fun MerchantAvailableMccCode.toOutputDTO() = MerchantAvailableMccCodeOutputDTO(
    id = this.id,
    merchant = this.merchant.toOutputDTO(),
    code = this.code
)

fun MerchantAvailableMccCodeOutputDTO.toEntity() = MerchantAvailableMccCode(
    id = this.id,
    merchant = this.merchant.toEntity(),
    code = this.code
)