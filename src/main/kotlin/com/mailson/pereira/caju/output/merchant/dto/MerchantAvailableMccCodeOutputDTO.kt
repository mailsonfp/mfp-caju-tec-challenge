package com.mailson.pereira.caju.output.merchant.dto

data class MerchantAvailableMccCodeOutputDTO (
    val id: Long? = null,
    val merchant: MerchantOutputDTO,
    val code: String
)