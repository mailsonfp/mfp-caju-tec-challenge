package com.mailson.pereira.caju.output.merchant.dto

data class MerchantOutputDTO (
    val id: Long? = null,
    val merchantName: String,
    val principalMCC: String
)