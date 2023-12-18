package id.ac.umn.kidshub.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{

    //Authentication Screen
    object MainScreen : Screen()
    object LoginScreen : Screen()
    object RegisterScreen : Screen()

    //Home Screen
    object HomeScreen : Screen()
    object BooksScreen : Screen()
    object ProfileScreen : Screen()

    //Details Screen
    data class VideosDetailScreen(val videoId: Int) : Screen()
}