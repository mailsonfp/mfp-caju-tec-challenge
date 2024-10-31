package com.mailson.pereira.caju.repository.implementation

import com.mailson.pereira.caju.input.account.AccountInput
import com.mailson.pereira.caju.input.customer.dto.CustomerInputDTO
import com.mailson.pereira.caju.output.account.AccountRepository
import com.mailson.pereira.caju.repository.jpainterfaces.CustomerJpaRepository
import com.mailson.pereira.caju.output.customer.CustomerRepository
import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import com.mailson.pereira.caju.repository.domain.toEntity
import com.mailson.pereira.caju.repository.domain.toOuputDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImplementation(
    private val customerJpaRepository: CustomerJpaRepository,
    private val accountRepository: AccountRepository
): CustomerRepository {
    override fun save(customerOutputDTO: CustomerOutputDTO): CustomerOutputDTO {
        return customerJpaRepository.save(customerOutputDTO.toEntity()).toOuputDTO()
    }

    override fun findByName(name: String): List<CustomerOutputDTO> {
        return customerJpaRepository.findByNameContains(name).map { it.toOuputDTO() }
    }

    override fun findByCode(code: String): CustomerOutputDTO? {
        return customerJpaRepository.findByCode(code)?.toOuputDTO()
    }

    override fun findAll(): List<CustomerOutputDTO> {
        return customerJpaRepository.findAll().map { it.toOuputDTO() }
    }

}