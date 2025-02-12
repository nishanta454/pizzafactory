package com.kotlin.pizzafactory.repository

import org.springframework.stereotype.Repository

@Repository
class InventoryRepository {
    private val inventory: MutableMap<String, Int> = mutableMapOf(
        "Deluxe Veggie" to 10, "Cheese and Corn" to 10, "Paneer Tikka" to 10,
        "Non-Veg Supreme" to 10, "Chicken Tikka" to 10, "Pepper Barbecue Chicken" to 10,
        "Cold drink" to 20, "Mousse cake" to 15
    )

    fun getStock(item: String): Int = inventory.getOrDefault(item, 0)

    fun updateStock(item: String, quantity: Int) {
        inventory[item] = (inventory[item] ?: 0) + quantity
    }

    fun deductStock(item: String, quantity: Int): Boolean {
        val currentStock = inventory.getOrDefault(item, 0)  
        return if (currentStock >= quantity) {
            inventory[item] = currentStock - quantity
            true
        } else {
            false
        }   
    }

    fun getAllInventory(): Map<String, Int> = inventory.toMap()
}