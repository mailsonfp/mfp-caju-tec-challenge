package com.mailson.pereira.caju.input.transaction

import com.mailson.pereira.caju.input.transaction.dto.request.TransactionInputRequestDTO
import com.mailson.pereira.caju.input.transaction.dto.response.TransactionResponseDTO

interface TransactionInput {
    fun authorize(transactionRequest: TransactionInputRequestDTO): TransactionResponseDTO
}