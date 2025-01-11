package com.example.newsapp.ui.screens.artsByCategoryScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.API_KEY
import com.example.newsapp.data.ServerApi
import com.example.newsapp.data.entity.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ArtsByCategoryScreenViewModel(private val api: ServerApi) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> get() = _articles

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun loadNewsByCategory(category: String) {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                Log.d("ArtsByCategory", "Loading arts for category: $category")
                val response = api.getNews(category, API_KEY) // category тут має бути просто назвою категорії
                if (response.isSuccessful) {
                    response.body()?.let {
                        _articles.value = it.articles.orEmpty()
                        Log.d("ArtsByCategory", "Articles loaded: ${it.articles.size}")
                    } ?: run {
                        _errorMessage.value = "No articles found."
                        Log.d("ArtsByCategory", "No articles found.")
                    }
                } else {
                    _errorMessage.value = "Failed to load arts: ${response.message()}"
                    Log.d("ArtsByCategory", "Error response: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
                Log.e("ArtsByCategory", "Error loading news", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}