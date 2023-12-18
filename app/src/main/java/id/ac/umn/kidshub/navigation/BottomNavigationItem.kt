package id.ac.umn.kidshub.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val listOfBottomNavigationItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home
    ),
    BottomNavigationItem(
        title = "Books",
        selectedIcon = Icons.Default.ShoppingCart,
        unselectedIcon = Icons.Default.ShoppingCart
    ),
    BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person
    )
)