package com.mailson.pereira.caju.input.transaction.dto.enums

enum class TransactionResponseCodeEnum(val responseCode: String) {
    AUTHORIZED("00"),
    DECLINED("51"),
    ERROR("07")
}