package com.example.fridgeapp.data

import com.example.fridgeapp.data.model.FoodItem
import com.example.fridgeapp.data.model.StorageLocation.*
import java.time.LocalDate

object SampleData {
    private val today = LocalDate.now()
    val items = listOf(
        FoodItem("1", "Milk", FRIDGE, today.plusDays(1), "Alex"),
        FoodItem("2", "Leftover pasta", FRIDGE, today.plusDays(2), "Sam"),
        FoodItem("3", "Eggs", FRIDGE, today.plusDays(12), "Alex", isRunningLow = true),
        FoodItem("4", "Hummus", FRIDGE, today.plusDays(6), "Jordan"),
        FoodItem("5", "Frozen pizza", FREEZER, today.plusDays(60), "Sam"),
        FoodItem("6", "Potatoes", CELLAR, today.plusDays(30), "Jordan")
    )
}