package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("v1/account")
class AccountController(
    private val accountInput: AccountInput
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody @Validated accountRequestInputDTO: AccountRequestInputDTO): ResponseEntity<Any>{
        val newAccount = accountInput.newAccount(accountRequestInputDTO)
        return ResponseEntity.ok(newAccount)
    }

    @PostMapping("/movements")
    fun movementAccount(@RequestBody accountMovementRequestInputDTO: AccountMovementRequestInputDTO): ResponseEntity<Any>{
        return ResponseEntity.created(URI.create("/v1/movement")).build()
    }
}