package com.kotlin.pizzafactory.controller

import com.kotlin.pizzafactory.service.InventoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inventory")
class InventoryController(private val inventoryService: InventoryService) {

    @GetMapping
    fun getInventory(): Map<String, Int> {
        return inventoryService.getInventory()
    }   

    @PostMapping("/restock")
    fun restockItem(@RequestParam item: String, @RequestParam quantity: Int): String {
        inventoryService.restockItem(item, quantity)
        return "Restocked $quantity units of $item."
    }
}