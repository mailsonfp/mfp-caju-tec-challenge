package com.mailson.pereira.caju.repository.implementation

import com.mailson.pereira.caju.output.merchant.MerchantRepository
import com.mailson.pereira.caju.output.merchant.dto.MerchantAvailableMccCodeOutputDTO
import com.mailson.pereira.caju.output.merchant.dto.MerchantOutputDTO
import com.mailson.pereira.caju.repository.domain.toEntity
import com.mailson.pereira.caju.repository.domain.toOutputDTO
import com.mailson.pereira.caju.repository.jpainterfaces.MerchantAvailableMccCodeJpaRepository
import com.mailson.pereira.caju.repository.jpainterfaces.MerchantJpaRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class MerchantRepositoryImplementation(
    private val merchantJpaRepository: MerchantJpaRepository,
    private val merchantAvailableMccCodeJpaRepository: MerchantAvailableMccCodeJpaRepository
): MerchantRepository {

    override fun save(merchantOutputDTO: MerchantOutputDTO): MerchantOutputDTO {
        return merchantJpaRepository.save(merchantOutputDTO.toEntity()).toOutputDTO()
    }

    override fun addAvailableMCC(merchantAvailableMccCodeOutputDTO: MerchantAvailableMccCodeOutputDTO) {
        merchantAvailableMccCodeJpaRepository.save(merchantAvailableMccCodeOutputDTO.toEntity())
    }

    override fun findAll(): List<MerchantOutputDTO> {
        return merchantJpaRepository.findAll().map { it.toOutputDTO() }
    }

    override fun findByMerchantName(merchantName: String): MerchantOutputDTO? {
        return merchantJpaRepository.findByMerchantName(merchantName).getOrNull()?.toOutputDTO()
    }

    override fun findExistAvailableMccCode(merchantName: String, mccCode: String): MerchantAvailableMccCodeOutputDTO? {
        return merchantAvailableMccCodeJpaRepository.findByMerchantAndMcc(merchantName, mccCode).getOrNull()?.toOutputDTO()
    }


}