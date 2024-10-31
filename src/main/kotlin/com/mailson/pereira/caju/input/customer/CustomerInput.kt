package com.mailson.pereira.caju.input.customer

import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.input.customer.dto.response.CustomerResponseInputDTO

interface CustomerInput {
    fun save(customerInputDTO: CustomerInputDTO): CustomerResponseInputDTO
    fun findByName(name: String): List<CustomerResponseInputDTO>
    fun findByCode(code: String): CustomerResponseInputDTO
    fun findAll(): List<CustomerResponseInputDTO>
}