package com.kotlin.pizzafactory.service

import com.kotlin.pizzafactory.model.*
import org.springframework.stereotype.Service

@Service
class MenuService {
    private val pizzas: MutableList<Pizza> = mutableListOf(
        Pizza("Deluxe Veggie", PizzaType.VEGETARIAN, PizzaSize.REGULAR, 150.0, Crust.HAND_TOSSED),
        Pizza("Cheese and Corn", PizzaType.VEGETARIAN, PizzaSize.REGULAR, 175.0, Crust.WHEAT_THIN_CRUST),
        Pizza("Paneer Tikka", PizzaType.VEGETARIAN, PizzaSize.REGULAR, 160.0, Crust.CHEESE_BURST),
        Pizza("Non-Veg Supreme", PizzaType.NON_VEGETARIAN, PizzaSize.REGULAR, 190.0, Crust.FRESHPAN_PIZZA),
        Pizza("Chicken Tikka", PizzaType.NON_VEGETARIAN, PizzaSize.REGULAR, 210.0, Crust.HAND_TOSSED)
    )

    private val toppings: MutableList<Topping> = mutableListOf(
        Topping("Black Olive", ToppingType.VEG, 20.0),
        Topping("Capsicum", ToppingType.VEG, 25.0),
        Topping("Paneer", ToppingType.VEG, 35.0),
        Topping("Chicken Tikka", ToppingType.NON_VEG, 35.0)
    )

    fun getPizzas(): List<Pizza> = pizzas

    fun addPizza(pizza: Pizza) {
        pizzas.add(pizza)
    }

    fun updatePizzaPrice(name: String, newPrice: Double) {
        pizzas.indexOfFirst { it.name == name }
            .takeIf { it != -1 }
            ?.let { index ->
                pizzas[index] = pizzas[index].copy(basePrice = newPrice)
            }
    }
}