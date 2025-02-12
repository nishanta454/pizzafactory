package com.kotlin.pizzafactory.controller

import com.kotlin.pizzafactory.model.Pizza
import com.kotlin.pizzafactory.service.MenuService

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuController(private val menuService: MenuService) {

    @GetMapping("/pizzas")
    fun getPizzas(): List<Pizza> {
        return menuService.getPizzas()
    }

    @PostMapping("/addPizza")
    fun addPizza(@RequestBody pizza: Pizza): String {
        menuService.addPizza(pizza)
        return "Pizza ${pizza.name} added successfully."
    }

    @PutMapping("/updatePrice")
    fun updatePizzaPrice(@RequestParam name: String, @RequestParam price: Double): String {
        menuService.updatePizzaPrice(name, price)
        return "Price updated for $name."
    }
}
