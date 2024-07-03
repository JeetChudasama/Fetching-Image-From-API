package com.example.fetchiamgefromapi.AppUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.fetchiamgefromapi.ViewModel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    // Retrieve the current states from the ViewModel
    val imageUrl = viewModel.imageUrl
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Displaying UI elements based on the current state
        when {
            isLoading -> {
                // Displaying loading indicator while fetching the image
                CircularProgressIndicator(color = Color.Cyan)
            }
            errorMessage != null -> {
                // Displaying error message
                Text(errorMessage, color = Color.Red)
            }
            imageUrl != null -> {
                Image(
                    // Displaying the fetched image
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .border(2.dp, Color.Cyan)
                        .padding(4.dp),
                    contentScale = ContentScale.Crop
                )
            }
            else -> {
                // Displaying a placeholder text before any image is loaded
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(2.dp, Color.Cyan),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Click the button to load an image", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to trigger image fetching
        Button(
            onClick = { viewModel.fetchImage() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan
            )
        ) {
            Text(
                text = "Load Image",
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen(MainViewModel())
    }
}
