package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("v1/account")
class AccountController {

    @PostMapping
    fun createAccount(@RequestBody accountRequestInputDTO: AccountRequestInputDTO): ResponseEntity<Any>{
        return ResponseEntity.created(URI.create("/v1/customer")).build()
    }

    @PostMapping("/movements")
    fun movementAccount(@RequestBody accountMovementRequestInputDTO: AccountMovementRequestInputDTO): ResponseEntity<Any>{
        return ResponseEntity.created(URI.create("/v1/movement")).build()
    }
}