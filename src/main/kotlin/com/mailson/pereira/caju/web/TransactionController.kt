package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.transaction.dto.TransactionInputDTO
import com.mailson.pereira.caju.input.transaction.dto.enums.TransactionResponseCodeEnum
import com.mailson.pereira.caju.input.transaction.dto.response.TransactionResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/transaction")
class TransactionController {

    @PostMapping("/authorize")
    fun authorize(@RequestBody transactionInputDTO: TransactionInputDTO): ResponseEntity<TransactionResponseDTO> {
        return ResponseEntity.ok(
            TransactionResponseDTO(
                code = TransactionResponseCodeEnum.AUTHORIZED.responseCode)
        )
    }
}