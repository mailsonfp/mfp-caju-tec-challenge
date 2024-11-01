package com.mailson.pereira.caju.input.transaction.dto.request

import java.math.BigDecimal

data class TransactionInputRequestDTO(
    val account: String,
    val mcc: String,
    val merchant: String,
    val totalAmount: BigDecimal
)