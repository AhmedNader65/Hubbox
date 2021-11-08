package com.hubbox.model

import com.hubbox.utils.OrderStatus


data class Spot(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val url: String,
    val orderStatus: OrderStatus
) {
    companion object {
        private var counter = 0L
    }
}