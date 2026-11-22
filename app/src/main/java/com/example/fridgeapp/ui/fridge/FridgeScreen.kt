package com.example.fridgeapp.ui.fridge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fridgeapp.data.SampleData
import com.example.fridgeapp.data.model.FoodItem
import com.example.fridgeapp.data.model.StorageLocation
import com.example.fridgeapp.ui.components.FoodItemCard
import com.example.fridgeapp.ui.components.StorageTabRow
import com.example.fridgeapp.ui.theme.FridgeAppTheme

@Composable
fun FridgeScreen(viewModel: FridgeViewModel) {
    val state by viewModel.uiState.collectAsState()
    FridgeContent(
        state = state,
        onLocationSelected = viewModel::onLocationSelected,
        onToggleRunningLow = viewModel::toggleRunningLow,
        onAddToShoppingList = viewModel::addToShoppingList,
        onDismissSuggestion = viewModel::dismissSuggestion
    )
}

@Composable
fun FridgeContent(
    state: FridgeUiState,
    onLocationSelected: (StorageLocation) -> Unit,
    onToggleRunningLow: (FoodItem) -> Unit,
    onAddToShoppingList: (FoodItem) -> Unit,
    onDismissSuggestion: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        StorageTabRow(selected = state.selectedLocation, onSelected = onLocationSelected)

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (state.expiringSoon.isNotEmpty()) {
                item { Text("Expiring Soon", style = MaterialTheme.typography.titleLarge) }
                items(state.expiringSoon, key = { it.id }) { item ->
                    FoodItemCard(
                        item = item,
                        onToggleRunningLow = { onToggleRunningLow(item) },
                        onAddToShoppingList = { onAddToShoppingList(item) }
                    )
                }
            }
            if (state.otherItems.isNotEmpty()) {
                item { Text("Everything else", style = MaterialTheme.typography.titleLarge) }
                items(state.otherItems, key = { it.id }) { item ->
                    FoodItemCard(
                        item = item,
                        onToggleRunningLow = { onToggleRunningLow(item) },
                        onAddToShoppingList = { onAddToShoppingList(item) }
                    )
                }
            }
        }
    }

    state.shoppingSuggestion?.let { item ->
        AlertDialog(
            onDismissRequest = onDismissSuggestion,
            title = { Text("Running low on ${item.name}") },
            text = { Text("Add it to your shopping list?") },
            confirmButton = { TextButton(onClick = { onAddToShoppingList(item) }) { Text("Add") } },
            dismissButton = { TextButton(onClick = onDismissSuggestion) { Text("Not now") } }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FridgeScreenPreview() {
    FridgeAppTheme {
        FridgeContent(
            state = FridgeUiState(
                expiringSoon = SampleData.items.take(2),
                otherItems = SampleData.items.drop(2).take(2)
            ),
            onLocationSelected = {}, onToggleRunningLow = {},
            onAddToShoppingList = {}, onDismissSuggestion = {}
        )
    }
}