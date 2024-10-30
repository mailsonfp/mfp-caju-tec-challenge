package com.mailson.pereira.caju.input.transaction.dto

import java.math.BigDecimal

data class TransactionInputDTO(
    val account: String,
    val mcc: String,
    val merchant: String,
    val totalAmount: BigDecimal
)