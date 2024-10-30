package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("v1/customer")
class CustomerController {

    @RequestMapping("/by-name")
    fun getCustomerByName(@RequestParam customerName: String): ResponseEntity<Any>{
        return ResponseEntity.ok("Retornar customer por nome")
    }

    @RequestMapping("/by-code/{customerCocde}")
    fun getCustomerByCode(@PathVariable customerCode: String): ResponseEntity<Any>{
        return ResponseEntity.ok("Retornar customer por código")
    }

    @RequestMapping("/by-code/{customerCocde}/accounts")
    fun getCustomerAccounts(@PathVariable customerCode: String): ResponseEntity<Any>{
        return ResponseEntity.ok("Retornar Contas do cliente")
    }

    @PostMapping
    fun newCustomer(@RequestBody newClientInfo: CustomerInputDTO): ResponseEntity<Any> {
        return ResponseEntity.created(URI.create("/v1/customer")).build()
    }
}