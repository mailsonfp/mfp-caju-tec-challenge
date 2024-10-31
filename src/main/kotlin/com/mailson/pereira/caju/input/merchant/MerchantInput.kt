package com.mailson.pereira.caju.input.merchant

import com.mailson.pereira.caju.input.merchant.dto.MerchantAvailableMccCodeInputDTO
import com.mailson.pereira.caju.input.merchant.dto.MerchantInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantAvailableMccRequestInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantRequestInputDTO
import com.mailson.pereira.caju.output.merchant.dto.MerchantAvailableMccCodeOutputDTO

interface MerchantInput {
    fun save(merchantRequestInputDTO: MerchantRequestInputDTO): MerchantInputDTO
    fun addAvailableMCC(merchantAvailableMccRequestInputDTO: MerchantAvailableMccRequestInputDTO)
    fun getAllMerchant(): List<MerchantInputDTO>
    fun findByMerchantName(merchantName: String): MerchantInputDTO
    fun findExistAvailableMccCode(merchantName: String, mccCode: String): MerchantAvailableMccCodeInputDTO?
}