package com.mailson.pereira.caju.web

import com.mailson.pereira.caju.input.merchant.MerchantInput
import com.mailson.pereira.caju.input.merchant.dto.MerchantInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantAvailableMccRequestInputDTO
import com.mailson.pereira.caju.input.merchant.dto.request.MerchantRequestInputDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/merchant")
class MerchantController(
    private val merchantInput: MerchantInput
) {

    @GetMapping("/all")
    fun getAllMerchant(): ResponseEntity<List<MerchantInputDTO>>{
        return ResponseEntity.ok(merchantInput.getAllMerchant())
    }

    @GetMapping("/by-name")
    fun getMerchantByName(@RequestParam merchantName: String): ResponseEntity<MerchantInputDTO>{
        return ResponseEntity.ok(merchantInput.findByMerchantName(merchantName))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun newMerchant(@RequestBody @Validated merchantRequestInputDTO: MerchantRequestInputDTO): ResponseEntity<MerchantInputDTO>{
        return ResponseEntity.ok(merchantInput.save(merchantRequestInputDTO))
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun newAvailableMerchant(@RequestBody @Validated merchantAvailableMccRequestInputDTO: MerchantAvailableMccRequestInputDTO): ResponseEntity<Any>{
        merchantInput.addAvailableMCC(merchantAvailableMccRequestInputDTO)
        return ResponseEntity.noContent().build()
    }
}