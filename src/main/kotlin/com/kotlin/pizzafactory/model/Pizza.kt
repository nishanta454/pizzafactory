package com.kotlin.pizzafactory.model

data class Pizza(
    val name: String,
    val type: PizzaType,
    val size: PizzaSize,
    val basePrice: Double,
    val crust: Crust,
    val toppings: MutableList<Topping> = mutableListOf()
)

enum class PizzaType { VEGETARIAN, NON_VEGETARIAN }
enum class PizzaSize { REGULAR, MEDIUM, LARGE }