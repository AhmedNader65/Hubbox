package com.hubbox.model


data class FAQ(
    val question: String,
    val answer: String,
    var minimized: Boolean =false

) {
}