package com.mailson.pereira.caju.output.merchant

import com.mailson.pereira.caju.output.merchant.dto.MerchantAvailableMccCodeOutputDTO
import com.mailson.pereira.caju.output.merchant.dto.MerchantOutputDTO

interface MerchantRepository {

    fun save(merchantOutputDTO: MerchantOutputDTO): MerchantOutputDTO
    fun addAvailableMCC(merchantAvailableMccCodeOutputDTO: MerchantAvailableMccCodeOutputDTO)
    fun findAll(): List<MerchantOutputDTO>
    fun findByMerchantName(merchantName: String): MerchantOutputDTO?
    fun findExistAvailableMccCode(merchantName: String, mccCode: String): MerchantAvailableMccCodeOutputDTO?
}