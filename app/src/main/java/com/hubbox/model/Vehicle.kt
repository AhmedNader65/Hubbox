package com.hubbox.model


data class Vehicle(
    val id: Long = counter++,
    val name: String,
    val size: String,
    val image: Int,
    val upto: Int,
) {
    companion object {
        private var counter = 0L
    }
}