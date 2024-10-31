package com.mailson.pereira.caju.service

import com.mailson.pereira.caju.input.account.dto.enums.AccountBalanceTypeEnum
import com.mailson.pereira.caju.input.exception.ExistingAvailableMerchantCodeException
import com.mailson.pereira.caju.input.exception.ExistingMerchantFoundException
import com.mailson.pereira.caju.input.exception.InvalidMovementBalanceTypeException
import com.mailson.pereira.caju.input.exception.MerchantNotFoundException
import com.mailson.pereira.caju.input.merchant.MerchantInput
import com.mailson.pereira.caju.input.merchant.dto.MerchantAvailableMccCodeInputDTO
import com.mailson.pereira.caju.input.merchant.dto.MerchantInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantAvailableMccRequestInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantRequestInputDTO
import com.mailson.pereira.caju.output.merchant.MerchantRepository
import com.mailson.pereira.caju.output.merchant.dto.MerchantAvailableMccCodeOutputDTO
import com.mailson.pereira.caju.output.merchant.dto.MerchantOutputDTO
import org.springframework.stereotype.Service

@Service
class MerchantService(
    private val merchantRepository: MerchantRepository,
): MerchantInput {

    override fun save(merchantRequestInputDTO: MerchantRequestInputDTO): MerchantInputDTO {
        lateinit var principalMCC: AccountBalanceTypeEnum

        try {
            principalMCC = AccountBalanceTypeEnum.valueOf(merchantRequestInputDTO.principalMCC)
        } catch (ex: IllegalArgumentException){
            throw InvalidMovementBalanceTypeException("${merchantRequestInputDTO.principalMCC} is not a valid Principal MCC")
        }

        val existingMerchant = try {
            findByMerchantName(merchantRequestInputDTO.merchantName)
        } catch (ex: MerchantNotFoundException){
            null
        }

        if (existingMerchant!= null)
            throw ExistingMerchantFoundException("Merchant with name=${existingMerchant.merchantName} already exists")

        return toMerchantInputDTO(merchantRepository.save(toMerchantOutputDTO(merchantRequestInputDTO, principalMCC)))
    }

    override fun addAvailableMCC(merchantAvailableMccRequestInputDTO: MerchantAvailableMccRequestInputDTO) {
        val merchant = findByMerchantName(merchantAvailableMccRequestInputDTO.merchantName)

        val existingCode = findExistAvailableMccCode(
            merchant.merchantName,
            merchantAvailableMccRequestInputDTO.mccCode
        )

        if (existingCode != null)
            throw ExistingAvailableMerchantCodeException("Code ${merchantAvailableMccRequestInputDTO.mccCode} already exists")

        merchantRepository.addAvailableMCC(toMerchantAvailableMccCodeOutputDTO(merchant, merchantAvailableMccRequestInputDTO.mccCode))
    }

    override fun getAllMerchant(): List<MerchantInputDTO> {
        return merchantRepository.findAll().map { toMerchantInputDTO(it) }
    }

    override fun findByMerchantName(merchantName: String): MerchantInputDTO {
        val merchant = merchantRepository.findByMerchantName(merchantName).takeIf { it != null } ?: throw MerchantNotFoundException("Unable to find a Merchant with name=$merchantName")
        return toMerchantInputDTO(merchant)
    }

    override fun findExistAvailableMccCode(merchantName: String, mccCode: String): MerchantAvailableMccCodeInputDTO? {
        val existingMccCode = merchantRepository.findExistAvailableMccCode(merchantName, mccCode).takeIf { it != null }
            ?: return null

        return toMerchantAvailableMccCodeInputDTO(existingMccCode)
    }

    private fun toMerchantInputDTO(merchantOutputDTO: MerchantOutputDTO): MerchantInputDTO {
        return MerchantInputDTO(
            id = merchantOutputDTO.id,
            merchantName = merchantOutputDTO.merchantName,
            principalMCC = AccountBalanceTypeEnum.valueOf(merchantOutputDTO.principalMCC)
        )
    }

    private fun toMerchantOutputDTO(merchantInputDTO: MerchantInputDTO): MerchantOutputDTO {
        return MerchantOutputDTO(
            id = merchantInputDTO.id,
            merchantName = merchantInputDTO.merchantName,
            principalMCC = merchantInputDTO.principalMCC.name
        )
    }

    private fun toMerchantOutputDTO(merchantRequestInputDTO: MerchantRequestInputDTO, principalMCC: AccountBalanceTypeEnum): MerchantOutputDTO {
        return MerchantOutputDTO(
            merchantName = merchantRequestInputDTO.merchantName,
            principalMCC = principalMCC.name
        )
    }

    fun toMerchantAvailableMccCodeInputDTO(merchantAvailableMccCodeOutputDTO: MerchantAvailableMccCodeOutputDTO): MerchantAvailableMccCodeInputDTO{
        return MerchantAvailableMccCodeInputDTO(
            id = merchantAvailableMccCodeOutputDTO.id,
            merchant = toMerchantInputDTO(merchantAvailableMccCodeOutputDTO.merchant),
            code = merchantAvailableMccCodeOutputDTO.code
        )
    }

    fun toMerchantAvailableMccCodeOutputDTO(merchant: MerchantInputDTO, mccCode: String): MerchantAvailableMccCodeOutputDTO{
        return MerchantAvailableMccCodeOutputDTO(
            merchant = toMerchantOutputDTO(merchant),
            code = mccCode
        )
    }
}