package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.customer.CustomerInput
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.input.customer.dto.response.CustomerResponseInputDTO
import com.mailson.pereira.caju.input.customer.dto.toResponse
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

    override fun save(customerInputDTO: CustomerInputDTO): CustomerResponseInputDTO {
        loggerWriter.info("method=save, status=init customer=$customerInputDTO")
        return toCustomerInputDTO(customerRepository.save(toCustomerOutputDTO(customerInputDTO))).toResponse()
    }

    override fun findByName(name: String): List<CustomerResponseInputDTO> {
        return customerRepository.findByName(name).map { toCustomerInputDTO(it).toResponse() }
    }

    override fun findByCode(code: String): CustomerResponseInputDTO {
        val customer = customerRepository.findByCode(code).takeIf { it != null } ?: throw CustomerNotFoundException("Unable to find client with code $code")

        return toCustomerInputDTO(customer).toResponse()
    }

    override fun findAll(): List<CustomerResponseInputDTO> {
        return customerRepository.findAll().map { toCustomerInputDTO(it).toResponse() }
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