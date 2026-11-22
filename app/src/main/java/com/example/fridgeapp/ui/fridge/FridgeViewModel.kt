package com.example.fridgeapp.ui.fridge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fridgeapp.data.model.FoodItem
import com.example.fridgeapp.data.model.StorageLocation
import com.example.fridgeapp.data.repository.FakeFoodRepository
import com.example.fridgeapp.data.repository.FoodRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class FridgeViewModel(
    private val repo: FoodRepository = FakeFoodRepository()
) : ViewModel() {

    private val selected = MutableStateFlow(StorageLocation.FRIDGE)
    private val suggestion = MutableStateFlow<FoodItem?>(null)

    val uiState: StateFlow<FridgeUiState> =
        combine(repo.items, selected, suggestion) { items, loc, sug ->
            val inLocation = items.filter { it.location == loc }.sortedBy { it.expiresOn }
            FridgeUiState(
                selectedLocation = loc,
                expiringSoon = inLocation.filter { it.daysLeft() <= 3 },
                otherItems = inLocation.filter { it.daysLeft() > 3 },
                shoppingSuggestion = sug
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), FridgeUiState())

    fun onLocationSelected(location: StorageLocation) { selected.value = location }

    fun toggleRunningLow(item: FoodItem) = viewModelScope.launch {
        val updated = item.copy(isRunningLow = !item.isRunningLow)
        repo.updateItem(updated)
        if (updated.isRunningLow && !updated.onShoppingList) suggestion.value = updated
    }

    fun addToShoppingList(item: FoodItem) = viewModelScope.launch {
        repo.updateItem(item.copy(onShoppingList = true))
        suggestion.value = null
    }

    fun dismissSuggestion() { suggestion.value = null }

    fun addItem(name: String, owner: String, daysUntilExpiry: Int, runningLow: Boolean) =
        viewModelScope.launch {
            repo.addItem(
                FoodItem(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    location = selected.value,
                    expiresOn = LocalDate.now().plusDays(daysUntilExpiry.toLong()),
                    ownerName = owner,
                    isRunningLow = runningLow
                )
            )
        }
}