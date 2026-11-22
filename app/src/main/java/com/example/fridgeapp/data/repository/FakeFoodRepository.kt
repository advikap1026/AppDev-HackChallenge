package com.example.fridgeapp.data.repository

import com.example.fridgeapp.data.SampleData
import com.example.fridgeapp.data.model.FoodItem
import kotlinx.coroutines.flow.*

class FakeFoodRepository : FoodRepository {
    private val _items = MutableStateFlow(SampleData.items)
    override val items: StateFlow<List<FoodItem>> = _items.asStateFlow()

    override suspend fun addItem(item: FoodItem) {
        _items.update { it + item }
    }

    override suspend fun updateItem(item: FoodItem) {
        _items.update { list -> list.map { if (it.id == item.id) item else it } }
    }
}