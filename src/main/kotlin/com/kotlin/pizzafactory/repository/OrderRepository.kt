package com.kotlin.pizzafactory.repository

import com.kotlin.pizzafactory.model.Order
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class OrderRepository {
    private val orders = mutableListOf<Order>()

    fun save(order: Order): Order {
        val newOrder = order.copy(id = UUID.randomUUID().toString())
        orders.add(newOrder)
        return newOrder
    }

    fun findAll(): List<Order> = orders

    fun findById(id: String): Order? = orders.find { it.id == id }
}