package com.eurofarma

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EurofarmaApplication

fun main(args: Array<String>) {
    runApplication<EurofarmaApplication>(*args)
}
