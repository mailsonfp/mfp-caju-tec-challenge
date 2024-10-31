package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.customer.CustomerInput
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.input.exception.CustomerNotFoundException
import com.mailson.pereira.caju.output.customer.CustomerRepository
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): CustomerInput {

    private val loggerWriter = LoggerFactory.getLogger(this::class.java)

    override fun save(customerInputDTO: CustomerInputDTO): CustomerInputDTO {
        loggerWriter.info("method=save, status=init customer=$customerInputDTO")
        return toCustomerInputDTO(customerRepository.save(toCustomerOutputDTO(customerInputDTO)))
    }

    override fun findByName(name: String): List<CustomerInputDTO> {
        return customerRepository.findByName(name).map { toCustomerInputDTO(it) }
    }

    override fun findByCode(code: String): CustomerInputDTO {
        val customer = customerRepository.findByCode(code).takeIf { it != null } ?: throw CustomerNotFoundException("Unable to find client with code $code")

        return toCustomerInputDTO(customer)
    }

    override fun findAll(): List<CustomerInputDTO> {
        return customerRepository.findAll().map { toCustomerInputDTO(it) }
    }

    private fun toCustomerOutputDTO(customerInputDTO: CustomerInputDTO): CustomerOutputDTO{
        return CustomerOutputDTO(
            id = customerInputDTO.id,
            code = customerInputDTO.code!!,
            name = customerInputDTO.name,
        )
    }

    private fun toCustomerInputDTO(customerOutputDTO: CustomerOutputDTO): CustomerInputDTO{
        return CustomerInputDTO(
            id = customerOutputDTO.id,
            code = customerOutputDTO.code!!,
            name = customerOutputDTO.name,
        )
    }
}