package com.example.artviewerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.artviewerapp.data.entity.ObjectDetailsResponse
import com.example.artviewerapp.data.repository.FavoritesRepository
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    var favorites by remember { mutableStateOf<List<ObjectDetailsResponse>>(emptyList()) }

    LaunchedEffect(Unit) {
        scope.launch {
            favorites = FavoritesRepository.getFavorites()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(favorites) { artwork ->
            Row(modifier = Modifier
                .padding(8.dp)
                .clickable { navController.navigate("details/${artwork.objectID}") }) {
                Image(
                    painter = rememberAsyncImagePainter(artwork.primaryImage),
                    contentDescription = artwork.title,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = artwork.title ?: "Untitled",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Рік: ${artwork.accessionYear ?: "Невідомо"}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
