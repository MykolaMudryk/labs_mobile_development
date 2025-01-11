package ui.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.ArtObject
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel(),
    onArtObject: (ArtObject) -> Unit
) {
    val articles by viewModel.artObjects.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchArt()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "General page with paintings",
                        style = MaterialTheme.typography.h6.copy(color = Color.White),
                        modifier = Modifier.padding(top = 30.dp)
                    )
                },
                backgroundColor = Color(0xFF005195),
                elevation = 8.dp,
                modifier = Modifier.height(80.dp)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center),
                        color = Color(0xFF005195)
                    )
                }
                errorMessage != null -> Text(
                    text = errorMessage ?: "",
                    style = MaterialTheme.typography.body2.copy(color = Color.Red),
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
                else -> NewsList(articles, onArticleClick)
            }
        }
    }
}
