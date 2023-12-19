package id.ac.umn.kidshub.navigation

sealed class Screen{

    //Authentication Screen
    object MainScreen : Screen()
    object LoginScreen : Screen()
    object RegisterScreen : Screen()

    //Home Screen
    object HomeScreen : Screen()
    object BooksScreen : Screen()
    object ProfileScreen : Screen()

    object AccountCenterScreen : Screen()

    object HelpAndRegulationsScreen : Screen()

    object AboutScreen : Screen()

    //Details Screen
    data class VideosDetailScreen(val videoId: String) : Screen()
}