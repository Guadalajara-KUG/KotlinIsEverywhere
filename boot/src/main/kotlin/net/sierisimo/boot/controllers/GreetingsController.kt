package net.sierisimo.boot.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class GreetingsController {

    @GetMapping
    fun greetings() = "Hi from SpringBoot!"

    @GetMapping("/{name}")
    fun greetings(@PathVariable("name") name: String) = "Hi $name from spring!!!"
}