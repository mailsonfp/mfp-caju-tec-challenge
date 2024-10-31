package com.mailson.pereira.caju.input.merchant.dto

data class MerchantAvailableMccCodeInputDTO (
    val id: Long? = null,
    val merchant: MerchantInputDTO,
    val code: String
)