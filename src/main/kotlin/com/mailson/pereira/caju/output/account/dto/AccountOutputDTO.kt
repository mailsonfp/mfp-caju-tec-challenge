package com.mailson.pereira.caju.output.account.dto

import com.mailson.pereira.caju.output.customer.dto.CustomerOutputDTO
import java.math.BigDecimal

data class AccountOutputDTO (
    val id: Long? = null,
    val customer: CustomerOutputDTO,
    val accountStatus: String
)