package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.customer.CustomerInput
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.input.customer.dto.CustomerRequestInputDTO
import com.mailson.pereira.caju.input.customer.dto.response.CustomerResponseInputDTO
import com.mailson.pereira.caju.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("v1/customer")
class CustomerController(
    private val customerInput: CustomerInput
) {
    @Operation(
        summary = "API to find all customers",
        responses = [ ApiResponse(responseCode = "200"), ApiResponse(responseCode = "404")]
    )
    @GetMapping("/all")
    fun getAllCustomers(): ResponseEntity<List<CustomerResponseInputDTO>>{
        return ResponseEntity.ok(customerInput.findAll())
    }

    @Operation(
        summary = "API to find customer by name",
        responses = [ ApiResponse(responseCode = "200"), ApiResponse(responseCode = "404")]
    )
    @GetMapping("/by-name")
    fun getCustomerByName(@RequestParam(required = true) customerName: String): ResponseEntity<List<CustomerResponseInputDTO>>{
        return ResponseEntity.ok(customerInput.findByName(customerName))
    }

    @Operation(
        summary = "API to find customer by code",
        responses = [ ApiResponse(responseCode = "200"), ApiResponse(responseCode = "404")]
    )
    @GetMapping("/by-code/{customerCode}")
    fun getCustomerByCode(@PathVariable customerCode: String): ResponseEntity<Any>{
        return ResponseEntity.ok(customerInput.findByCode(customerCode))
    }

    @GetMapping("/by-code/{customerCode}/accounts")
    fun getCustomerAccounts(@PathVariable customerCode: String): ResponseEntity<Any>{
        return ResponseEntity.ok("Retornar Contas do cliente")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "API to create new customer",
        responses = [ ApiResponse(responseCode = "201")]
    )
    fun newCustomer(@RequestBody @Validated newClientInfo: CustomerRequestInputDTO): ResponseEntity<CustomerResponseInputDTO> {
        val newCustomer = customerInput.save(CustomerInputDTO(name = newClientInfo.name))
        return ResponseEntity.ok(newCustomer)
    }
}