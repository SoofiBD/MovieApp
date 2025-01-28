package com.example.movieapp.presentation.opening // Update with your package name

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.presentation.Screen // Update with your Screen class

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpeningPage(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie_screen")
    // ... other screens
}

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Movie Mania",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome to Movie Mania!",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { navController.navigate(Screen.MovieScreen.route) }) {
                Text("Browse Movies")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* Navigate to Search */ }) {
                Text("Search Movies")
            }
        }
    }
}
