package com.example.fetchiamgefromapi.ViewModel

import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchiamgefromapi.Api.ApiInstance
import retrofit2.Retrofit

class MainViewModel : ViewModel() {
    // State to hold the URL of the fetched image
    var imageUrl by mutableStateOf<String?>(null)
        private set

    // State to indicate loading status
    var isLoading by mutableStateOf(false)
        private set

    // State to hold error messages
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Function to fetch image from the API
    fun fetchImage(){
        isLoading = true
        errorMessage = null

        viewModelScope.launch(Dispatchers.IO){
            try {
                // API call to fetch the image
                val response = ApiInstance.api.getImage()
                if (response.isNotEmpty()){
                    withContext(Dispatchers.Main) {
                        imageUrl = response[0].url
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        errorMessage = "No image found."
                    }
                }
            }catch (e: Exception) {
                // Handling any exceptions that occur during the API call
                withContext(Dispatchers.Main) {
                    errorMessage = "Failed to load image. Please check your internet connection."
                }
            } finally {
                // Set loading state to false after the API call is completed
                withContext(Dispatchers.Main) {
                    isLoading = false
                }
            }
        }
    }
}