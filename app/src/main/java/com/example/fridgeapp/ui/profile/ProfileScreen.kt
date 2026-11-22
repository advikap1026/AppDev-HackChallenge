package com.example.fridgeapp.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fridgeapp.ui.theme.FridgeAppTheme

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile (coming soon)")
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    FridgeAppTheme { ProfileScreen() }
}