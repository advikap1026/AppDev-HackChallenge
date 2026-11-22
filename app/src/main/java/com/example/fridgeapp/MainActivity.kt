package com.example.fridgeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fridgeapp.ui.navigation.AppNavHost
import com.example.fridgeapp.ui.theme.FridgeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FridgeAppTheme {
                AppNavHost()
            }
        }
    }
}