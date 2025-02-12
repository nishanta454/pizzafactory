package com.kotlin.pizzafactory.service

import com.kotlin.pizzafactory.model.*
import com.kotlin.pizzafactory.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val inventoryService: InventoryService,
    private val orderRepository: OrderRepository
) {

    fun placeOrder(pizzas: List<Pizza>, sides: List<Side>): Order? {
        if (!validateOrder(pizzas) || !checkInventory(pizzas, sides)) return null

        val totalAmount = calculateTotal(pizzas, sides)
        val order = Order(UUID.randomUUID().toString(), pizzas, sides, totalAmount)

        val savedOrder = orderRepository.save(order)
        pizzas.forEach { inventoryService.reduceStock(it.name, 1) }
        sides.forEach { inventoryService.reduceStock(it.name, 1) }

        return savedOrder
    }

    private fun checkInventory(pizzas: List<Pizza>, sides: List<Side>): Boolean {
        return (pizzas.all { inventoryService.isAvailable(it.name, 1) } &&
                sides.all { inventoryService.isAvailable(it.name, 1) })
    }

    private fun validateOrder(pizzas: List<Pizza>): Boolean {
        return pizzas.all { pizza ->
            when (pizza.type) {
                PizzaType.VEGETARIAN -> pizza.toppings.none { it.type == ToppingType.NON_VEG }
                PizzaType.NON_VEGETARIAN -> pizza.toppings.none { it.name == "Paneer" }
            }
        }
    }

    private fun calculateTotal(pizzas: List<Pizza>, sides: List<Side>): Double {
        return pizzas.sumOf { it.basePrice + it.toppings.sumOf { t -> t.price } } +
               sides.sumOf { it.price }
    }

    fun getAllOrders(): List<Order> = orderRepository.findAll()

    fun getOrderById(orderId: String): Order? = orderRepository.findById(orderId)
}
