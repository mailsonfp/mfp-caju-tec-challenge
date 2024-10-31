package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.MerchantAvailableMccCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface MerchantAvailableMccCodeJpaRepository : JpaRepository<MerchantAvailableMccCode, Long>{

    @Query("from MerchantAvailableMccCode mcc where mcc.merchant.merchantName = :merchantName and mcc.code = :mccCode")
    fun findByMerchantAndMcc(merchantName: String, mccCode: String): Optional<MerchantAvailableMccCode>
}