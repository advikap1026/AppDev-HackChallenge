package com.example.fridgeapp.ui.fridge

import com.example.fridgeapp.data.model.FoodItem
import com.example.fridgeapp.data.model.StorageLocation

data class FridgeUiState(
    val selectedLocation: StorageLocation = StorageLocation.FRIDGE,
    val expiringSoon: List<FoodItem> = emptyList(),
    val otherItems: List<FoodItem> = emptyList(),
    val shoppingSuggestion: FoodItem? = null   // non-null = show "add to list?" dialog
)