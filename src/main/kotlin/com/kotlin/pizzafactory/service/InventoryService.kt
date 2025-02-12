package com.kotlin.pizzafactory.service

import com.kotlin.pizzafactory.model.*
import com.kotlin.pizzafactory.repository.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(private val inventoryRepository: InventoryRepository) {

    fun isAvailable(item: String, quantity: Int): Boolean {
        return inventoryRepository.getStock(item) >= quantity
    }

    fun restockItem(item: String, quantity: Int) {
        inventoryRepository.updateStock(item, quantity)
    }

    fun reduceStock(item: String, quantity: Int): Boolean {
        return inventoryRepository.deductStock(item, quantity)
    }

    fun getInventory(): Map<String, Int> {
        return inventoryRepository.getAllInventory()
    }
}