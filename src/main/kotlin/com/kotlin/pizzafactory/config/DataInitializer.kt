package com.kotlin.pizzafactory.config

import com.kotlin.pizzafactory.model.*
import com.kotlin.pizzafactory.repository.InventoryRepository
import com.kotlin.pizzafactory.repository.OrderRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitializer {

    @Bean
    fun initializeData(inventoryRepository: InventoryRepository, orderRepository: OrderRepository) = CommandLineRunner {
        val pizzas = listOf(
            Pizza("Deluxe Veggie", PizzaType.VEGETARIAN, PizzaSize.MEDIUM, 8.99, Crust.WHEAT_THIN_CRUST, mutableListOf()),
            Pizza("Chicken Tikka", PizzaType.NON_VEGETARIAN, PizzaSize.LARGE, 10.99, Crust.FRESHPAN_PIZZA, mutableListOf())
        )


        val sides = listOf(
            Side("Cold drink", 2.50),
            Side("Mousse cake", 3.75)
        )

        pizzas.forEach { inventoryRepository.updateStock(it.name, 10) }
        sides.forEach { inventoryRepository.updateStock(it.name, 20) }
    }
}
