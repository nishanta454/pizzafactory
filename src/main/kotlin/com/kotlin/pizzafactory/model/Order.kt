    package com.kotlin.pizzafactory.model

    data class Order(
        val id: String,
        val pizzas: List<Pizza>,
        val sides: List<Side>,
        val totalAmount: Double
    )