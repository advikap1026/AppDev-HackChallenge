package com.example.fridgeapp.data.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class FoodItem(
    val id: String,
    val name: String,
    val location: StorageLocation,
    val expiresOn: LocalDate,
    val ownerName: String,
    val isRunningLow: Boolean = false,
    val onShoppingList: Boolean = false
) {
    fun daysLeft(today: LocalDate = LocalDate.now()): Int =
        ChronoUnit.DAYS.between(today, expiresOn).toInt()
}