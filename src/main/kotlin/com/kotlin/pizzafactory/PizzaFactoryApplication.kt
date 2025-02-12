package com.kotlin.pizzafactory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PizzaFactoryApplication

fun main(args: Array<String>) {
	runApplication<PizzaFactoryApplication>(*args)
}
