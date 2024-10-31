package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.account.dto.AccountInputDTO
import com.mailson.pereira.caju.input.account.dto.enums.AccountStatusEnum
import com.mailson.pereira.caju.input.account.dto.request.AccountMovementRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("v1/account")
class AccountController(
    private val accountInput: AccountInput
) {
    @GetMapping("/accounts-by-customer")
    fun getAccountsByCustomerCode(@RequestParam(required = true) customerCode: String): ResponseEntity<List<AccountInputDTO>> {
        return ResponseEntity.ok(accountInput.findAccountsByCustomerCode(customerCode))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAccount(@RequestBody @Validated accountRequestInputDTO: AccountRequestInputDTO): ResponseEntity<Any>{
        val newAccount = accountInput.newAccount(accountRequestInputDTO)
        return ResponseEntity.ok(newAccount)
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun accountWithBalance(@PathVariable accountId: String): ResponseEntity<Any>{
        return ResponseEntity.ok(accountInput.getAccountAndBalanceById(accountId))
    }

    @DeleteMapping("/{accountId}")
    fun inactivateAccount(@PathVariable accountId: String): ResponseEntity<Any>{
        accountInput.manageAccountStatus(accountId, AccountStatusEnum.INACTIVE)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{accountId}")
    fun activateAccount(@PathVariable accountId: String): ResponseEntity<Any>{
        accountInput.manageAccountStatus(accountId, AccountStatusEnum.ACTIVE)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/movements")
    @ResponseStatus(HttpStatus.CREATED)
    fun movementAccount(@RequestBody @Validated accountMovementRequestInputDTO: AccountMovementRequestInputDTO): ResponseEntity<Any>{
        accountInput.movement(accountMovementRequestInputDTO)
        return ResponseEntity.noContent().build()
    }
}