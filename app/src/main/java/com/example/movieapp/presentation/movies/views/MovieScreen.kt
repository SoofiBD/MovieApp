package com.example.movieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.movies.MoviesEvent
import com.example.movieapp.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)) {
        MovieSearchBar(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            hint = "Search",
            onSearch = {
                viewModel.onEvent(MoviesEvent.Search(it))
            })

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            ) {
                items(state.movies) {movie ->
                    MovieListRow(movie = movie , onItemClick = {
                    navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                    //navController.navigate("movie_detail_screen/${movie.imdbID}")
                    })
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint: String = "Search",
    onSearch : (String) -> Unit = {},
){
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    //val state = viewModel.state.value

    Box(modifier = modifier) {

        TextField(value = text,
            onValueChange = {
                text = it
            } ,
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                //backgroundColor = Color.DarkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .shadow(4.dp)
                .background(Color.White, CircleShape)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                }
        )

        if(isHintDisplayed) {
            Text(text = hint , color = Color.LightGray , modifier = Modifier.padding(8.dp))
        }
    }
}

