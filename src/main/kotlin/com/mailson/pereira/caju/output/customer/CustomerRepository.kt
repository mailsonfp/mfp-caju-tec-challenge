package com.mailson.pereira.caju.output.customer

import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO

interface CustomerRepository {
    fun save(customerOutputDTO: CustomerOutputDTO): CustomerOutputDTO
    fun findByName(name: String): List<CustomerOutputDTO>
    fun findByCode(code: String): CustomerOutputDTO?
    fun findAll(): List<CustomerOutputDTO>
}