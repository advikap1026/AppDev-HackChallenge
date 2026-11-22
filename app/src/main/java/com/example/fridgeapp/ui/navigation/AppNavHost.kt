package com.example.fridgeapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fridgeapp.ui.add.AddItemScreen
import com.example.fridgeapp.ui.components.AppBottomBar
import com.example.fridgeapp.ui.fridge.FridgeScreen
import com.example.fridgeapp.ui.fridge.FridgeViewModel
import com.example.fridgeapp.ui.profile.ProfileScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val fridgeViewModel: FridgeViewModel = viewModel()   // shared by Fridge + Add screens

    Scaffold(
        bottomBar = {
            AppBottomBar(
                onHome = { navController.goTo(Screen.Fridge) },
                onAdd = { navController.goTo(Screen.AddItem) },
                onProfile = { navController.goTo(Screen.Profile) }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Fridge.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Fridge.route) { FridgeScreen(fridgeViewModel) }
            composable(Screen.AddItem.route) {
                AddItemScreen(onSave = { name, owner, days, low ->
                    fridgeViewModel.addItem(name, owner, days, low)
                    navController.popBackStack()
                })
            }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

private fun NavHostController.goTo(screen: Screen) {
    navigate(screen.route) {
        popUpTo(Screen.Fridge.route)
        launchSingleTop = true
    }
}