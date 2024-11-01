package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.transaction.TransactionInput
import com.mailson.pereira.caju.input.transaction.dto.request.TransactionInputRequestDTO
import com.mailson.pereira.caju.input.transaction.dto.enums.TransactionResponseCodeEnum
import com.mailson.pereira.caju.input.transaction.dto.response.TransactionResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/transaction")
class TransactionController(
    private val transactionInput: TransactionInput
) {

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.OK)
    fun authorize(@RequestBody transactionInputRequestDTO: TransactionInputRequestDTO): ResponseEntity<TransactionResponseDTO> {
        return ResponseEntity.ok(
            transactionInput.authorize(transactionInputRequestDTO)
        )
    }
}