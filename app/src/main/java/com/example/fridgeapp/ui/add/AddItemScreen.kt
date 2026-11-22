package com.example.fridgeapp.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fridgeapp.ui.theme.FridgeAppTheme

@Composable
fun AddItemScreen(onSave: (name: String, owner: String, days: Int, runningLow: Boolean) -> Unit) {
    var name by remember { mutableStateOf("") }
    var owner by remember { mutableStateOf("") }
    var days by remember { mutableStateOf("") }
    var runningLow by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Add grocery", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(name, { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(owner, { owner = it }, label = { Text("Who owns it?") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = days,
            onValueChange = { days = it.filter(Char::isDigit) },
            label = { Text("Days until it expires") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Running low", Modifier.weight(1f))
            Switch(runningLow, { runningLow = it })
        }
        Button(
            onClick = { onSave(name.trim(), owner.trim().ifBlank { "Me" }, days.toIntOrNull() ?: 7, runningLow) },
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Save") }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddItemScreenPreview() {
    FridgeAppTheme { AddItemScreen(onSave = { _, _, _, _ -> }) }
}