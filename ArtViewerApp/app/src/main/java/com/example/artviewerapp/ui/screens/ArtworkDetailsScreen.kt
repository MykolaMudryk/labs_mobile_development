package com.example.artviewerapp.ui.screens

import com.example.artviewerapp.data.repository.FavoritesRepository
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.artviewerapp.data.entity.ObjectDetailsResponse
import kotlinx.coroutines.launch
import com.example.artviewerapp.data.remote.RetrofitInstance

@Composable
fun ArtworkDetailsScreen(artworkID: Int) {
    val scope = rememberCoroutineScope()
    var artwork by remember { mutableStateOf<ObjectDetailsResponse?>(null) }
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(artworkID) {
        scope.launch {
            artwork = RetrofitInstance.api.getObjectDetailsByObjectID(artworkID)
            isFavorite = artwork?.let { FavoritesRepository.isFavorite(it) } ?: false
        }
    }

    artwork?.let {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(it.primaryImage),
                contentDescription = it.title,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.title ?: "Untitled", style = MaterialTheme.typography.titleLarge)
            Text(text = "Artist: ${it.artistDisplayName ?: "Unknown"}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Bio: ${it.artistDisplayBio ?: "No biography available"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    if (isFavorite) {
                        FavoritesRepository.removeFromFavorites(it)
                    } else {
                        FavoritesRepository.addToFavorites(it)
                    }
                    isFavorite = !isFavorite
                }
            }) {
                Text(if (isFavorite) "Забрати з Вподобаних" else "Вподобати")
            }
        }
    }
}