package ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import data.MetMuseumApi
import data.ArtObject

class MainScreenViewModel(private val metMuseumApi: MetMuseumApi) : ViewModel() {

    private val _artObjects = MutableStateFlow<List<ArtObject>>(emptyList())
    val artObjects: StateFlow<List<ArtObject>> = _artObjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // шукаємо "painting" з зображеннями для головного екрану
    fun fetchArt(query: String = "painting") {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // 1. Шукаємо об’єкти за ключовим словом "painting"
                val searchResponse = metMuseumApi.searchArtObjects(query = query, hasImages = true)
                if (searchResponse.isSuccessful && searchResponse.body() != null) {
                    val objectIDs = searchResponse.body()!!.objectIDs ?: emptyList()

                    // 2. Тепер треба отримати деталі кожного ID
                    val tempList = mutableListOf<ArtObject>()
                    // Обмежимося, наприклад, 10 першими об’єктами,
                    // щоби не робити багато запитів відразу
                    for (id in objectIDs.take(10)) {
                        val detailResponse = metMuseumApi.getArtObjectById(id)
                        if (detailResponse.isSuccessful && detailResponse.body() != null) {
                            tempList.add(detailResponse.body()!!)
                        } else {
                            // Можна зберегти в лог чи обробити помилку
                            Log.e("MainScreenViewModel", "Detail fetch error: ${detailResponse.code()}")
                        }
                    }

                    // 3. Записуємо список у _artObjects
                    _artObjects.value = tempList
                } else {
                    _errorMessage.value = "Error: ${searchResponse.code()} ${searchResponse.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch art: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
