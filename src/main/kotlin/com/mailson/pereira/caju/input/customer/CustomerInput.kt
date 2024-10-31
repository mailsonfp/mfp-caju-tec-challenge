package com.mailson.pereira.caju.input.customer

import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO

interface CustomerInput {
    fun save(customerInputDTO: CustomerInputDTO): CustomerInputDTO
    fun findByName(name: String): List<CustomerInputDTO>
    fun findByCode(code: String): CustomerInputDTO
    fun findAll(): List<CustomerInputDTO>
}