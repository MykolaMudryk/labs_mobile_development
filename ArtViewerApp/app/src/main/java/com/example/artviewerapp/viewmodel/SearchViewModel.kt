package com.example.artviewerapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artviewerapp.data.entity.ObjectDetailsResponse
import com.example.artviewerapp.data.remote.fetchArtworksWithQuery
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var searchQuery = mutableStateOf("")
        private set

    var searchResults = SnapshotStateList<ObjectDetailsResponse>()
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun performSearch() {
        viewModelScope.launch {
            isLoading.value = true
            searchResults.clear()
            val results = fetchArtworksWithQuery(searchQuery.value, limit = 10)
            searchResults.addAll(results)
            isLoading.value = false
        }
    }
}
