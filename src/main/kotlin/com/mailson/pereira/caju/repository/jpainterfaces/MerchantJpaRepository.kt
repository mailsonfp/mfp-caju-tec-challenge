package com.mailson.pereira.caju.repository.jpainterfaces

import com.mailson.pereira.caju.repository.domain.Merchant
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MerchantJpaRepository : JpaRepository<Merchant, Long> {

    fun findByMerchantName(merchantName: String): Optional<Merchant>
}