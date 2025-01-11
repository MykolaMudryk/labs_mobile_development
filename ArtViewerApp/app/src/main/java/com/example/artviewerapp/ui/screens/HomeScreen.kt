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
import com.example.artviewerapp.data.remote.fetchRandomArtworks
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavHostController) {
    RandomArtworkScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomArtworkScreen(navController: NavHostController) {
    val artworks = remember { mutableStateListOf<ObjectDetailsResponse>() }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            isLoading = true
            val initialArtworks = fetchRandomArtworks(limit = 20)
            artworks.addAll(initialArtworks)
            isLoading = false
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(artworks) { artwork ->
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

        item {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else {
                LaunchedEffect(artworks.size) {
                    scope.launch {
                        isLoading = true
                        val newArtworks = fetchRandomArtworks(limit = 10)
                        artworks.addAll(newArtworks)
                        isLoading = false
                    }
                }
            }
        }
    }
}