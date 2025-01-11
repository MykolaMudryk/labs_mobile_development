package com.example.artviewerapp.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artviewerapp.ui.screens.ArtworkDetailsScreen
import com.example.artviewerapp.ui.screens.FavoritesScreen
import com.example.artviewerapp.ui.screens.HomeScreen
import com.example.artviewerapp.ui.screens.SearchScreen
import com.example.artviewerapp.viewmodel.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("search") { backStackEntry ->
                val viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(backStackEntry)
                SearchScreen(navController, viewModel)
            }
            composable("favorites") { FavoritesScreen(navController) }
            composable("details/{artworkID}") { backStackEntry ->
                val artworkID = backStackEntry.arguments?.getString("artworkID")?.toIntOrNull()
                if (artworkID != null) {
                    ArtworkDetailsScreen(artworkID)
                } else {
                    Text("Invalid artwork ID")
                }
            }
        }
    }
}
