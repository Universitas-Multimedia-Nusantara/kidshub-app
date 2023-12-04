package id.ac.umn.kidshub.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem (
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screen.HomeScreen.route
    ),
    NavItem(
        label = "Books",
        icon = Icons.Default.ShoppingCart,
        route = Screen.BooksScreen.route
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.Person,
        route = Screen.ProfileScreen.route
    )
)