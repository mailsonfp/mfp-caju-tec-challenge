package com.mailson.pereira.caju.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/teste")
@RestController
class Teste {
    @GetMapping
    fun getHelloWord(): ResponseEntity<Any>{
        return ResponseEntity.ok("Deu certo cario")
    }
}