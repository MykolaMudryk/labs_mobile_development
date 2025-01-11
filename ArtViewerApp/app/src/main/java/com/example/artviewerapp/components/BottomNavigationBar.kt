package com.example.artviewerapp.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.artviewerapp.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") },
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_home),
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == "search",
            onClick = { navController.navigate("search") },
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_search),
                    contentDescription = "Search"
                )
            },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == "favorites",
            onClick = { navController.navigate("favorites") },
            icon = {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_favorite),
                    contentDescription = "Favorites"
                )
            },
            label = { Text("Favorites") }
        )
    }
}
