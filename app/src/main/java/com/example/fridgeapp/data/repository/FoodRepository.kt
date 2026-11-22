package com.example.fridgeapp.data.repository

import com.example.fridgeapp.data.model.FoodItem
import kotlinx.coroutines.flow.StateFlow

interface FoodRepository {
    val items: StateFlow<List<FoodItem>>
    suspend fun addItem(item: FoodItem)
    suspend fun updateItem(item: FoodItem)
}