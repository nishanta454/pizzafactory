package com.kotlin.pizzafactory.model

data class Topping(
    val name: String,
    val type: ToppingType,
    val price: Double
)

enum class ToppingType { VEG, NON_VEG }