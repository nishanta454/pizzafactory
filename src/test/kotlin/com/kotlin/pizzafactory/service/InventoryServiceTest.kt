package com.kotlin.pizzafactory.service

import com.kotlin.pizzafactory.repository.InventoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class InventoryServiceTest {

    @Mock
    private lateinit var inventoryRepository: InventoryRepository

    @InjectMocks
    private lateinit var inventoryService: InventoryService

    @Test
    fun `isAvailable should return true if stock is sufficient`() {
        `when`(inventoryRepository.getStock("Cheese and Corn")).thenReturn(10)

        val result = inventoryService.isAvailable("Cheese and Corn", 5)
        assertTrue(result)
    }

    @Test
    fun `isAvailable should return false if stock is insufficient`() {
        `when`(inventoryRepository.getStock("Garlic Bread")).thenReturn(2)

        val result = inventoryService.isAvailable("Garlic Bread", 5)
        assertFalse(result)
    }

    @Test
    fun `restockItem should call updateStock`() {
        inventoryService.restockItem("Cheese and Corn", 5)
        verify(inventoryRepository).updateStock("Cheese and Corn", 5)
    }

    @Test
    fun `reduceStock should return true when stock deduction is successful`() {
        `when`(inventoryRepository.deductStock("Cheese and Corn", 2)).thenReturn(true)

        val result = inventoryService.reduceStock("Cheese and Corn", 2)
        assertTrue(result)
        verify(inventoryRepository).deductStock("Cheese and Corn", 2)
    }

    @Test
    fun `reduceStock should return false when stock deduction fails`() {
        `when`(inventoryRepository.deductStock("Garlic Bread", 5)).thenReturn(false)

        val result = inventoryService.reduceStock("Garlic Bread", 5)
        assertFalse(result)
        verify(inventoryRepository).deductStock("Garlic Bread", 5)
    }

    @Test
    fun `getInventory should return all inventory items`() {
        val mockInventory = mapOf("Cheese and Corn" to 10, "Garlic Bread" to 5)
        `when`(inventoryRepository.getAllInventory()).thenReturn(mockInventory)

        val inventory = inventoryService.getInventory()
        assertEquals(mockInventory, inventory)
    }
}
