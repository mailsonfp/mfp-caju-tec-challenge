package com.mailson.pereira.caju.input.transaction.dto.enums

import com.mailson.pereira.caju.input.account.dto.enums.AccountBalanceTypeEnum

enum class TransactionMccCodeEnum(val mccCode: String, val accountBalanceTypeEnum: AccountBalanceTypeEnum) {
    MCC5411("5411", AccountBalanceTypeEnum.FOOD),
    MCC5412("5412", AccountBalanceTypeEnum.FOOD),
    MCC5811("5811", AccountBalanceTypeEnum.MEAL),
    MCC5812("5812", AccountBalanceTypeEnum.MEAL),
    MCCDEFAULT("0", AccountBalanceTypeEnum.CASH);

    companion object {
        fun getAccountBalanceTypeEnum(mccCode: String): AccountBalanceTypeEnum{
            val mccCodeEnum = TransactionMccCodeEnum.values().firstOrNull{ it.mccCode == mccCode }

            return mccCodeEnum?.accountBalanceTypeEnum ?: MCCDEFAULT.accountBalanceTypeEnum
        }
    }
}