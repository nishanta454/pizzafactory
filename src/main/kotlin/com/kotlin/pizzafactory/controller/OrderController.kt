package com.kotlin.pizzafactory.controller

import com.kotlin.pizzafactory.model.Order
import com.kotlin.pizzafactory.model.Pizza
import com.kotlin.pizzafactory.model.Side
import com.kotlin.pizzafactory.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/place")
    fun placeOrder(@RequestBody orderRequest: OrderRequest): Order? {
        return orderService.placeOrder(orderRequest.pizzas, orderRequest.sides)
    }
}

data class OrderRequest(
    val pizzas: List<Pizza>,
    val sides: List<Side>
)
