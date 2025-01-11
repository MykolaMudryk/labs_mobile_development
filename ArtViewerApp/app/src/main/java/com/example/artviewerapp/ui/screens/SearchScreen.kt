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
import com.example.artviewerapp.viewmodel.SearchViewModel

@Composable
fun SearchScreen(navController: NavHostController, viewModel: SearchViewModel) {
    val searchQuery = viewModel.searchQuery.value
    val searchResults = viewModel.searchResults
    val isLoading = viewModel.isLoading.value

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Введіть запит") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.performSearch() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Пошук")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(searchResults) { artwork ->
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
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}