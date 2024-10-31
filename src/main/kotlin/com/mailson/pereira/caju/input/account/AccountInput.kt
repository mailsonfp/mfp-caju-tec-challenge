package com.mailson.pereira.caju.input.account

import com.mailson.pereira.caju.input.account.dto.request.AccountRequestInputDTO
import com.mailson.pereira.caju.input.account.dto.response.AccountResponseInputDTO

interface AccountInput {
    fun newAccount(accountRequestInputDTO: AccountRequestInputDTO): AccountResponseInputDTO
}