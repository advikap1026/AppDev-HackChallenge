package com.example.fridgeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Fridge : Screen("fridge")
    data object AddItem : Screen("add_item")
    data object Profile : Screen("profile")
}