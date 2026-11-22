package com.example.fridgeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fridgeapp.data.model.FoodItem

@Composable
fun FoodItemCard(
    item: FoodItem,
    onToggleRunningLow: () -> Unit,
    onAddToShoppingList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val days = item.daysLeft()
    val expiry = when {
        days < 0 -> "Expired"
        days == 0 -> "Expires today"
        days == 1 -> "1 day left"
        else -> "$days days left"
    }

    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text("$expiry · ${item.ownerName}", style = MaterialTheme.typography.bodyMedium)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Running low", style = MaterialTheme.typography.labelSmall)
                Switch(checked = item.isRunningLow, onCheckedChange = { onToggleRunningLow() })
            }
            IconButton(onClick = onAddToShoppingList, enabled = !item.onShoppingList) {
                Icon(
                    imageVector = if (item.onShoppingList) Icons.Default.Check else Icons.Default.ShoppingCart,
                    contentDescription = "Add to shopping list"
                )
            }
        }
    }
}