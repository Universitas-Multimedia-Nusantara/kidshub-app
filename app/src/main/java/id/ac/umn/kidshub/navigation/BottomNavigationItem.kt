package id.ac.umn.kidshub.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.OndemandVideo
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val listOfBottomNavigationItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Rounded.Home,
    ),
    BottomNavigationItem(
        title = "Videos",
        selectedIcon = Icons.Rounded.OndemandVideo,
        unselectedIcon = Icons.Rounded.OndemandVideo,
    ),
    BottomNavigationItem(
        title = "Books",
        selectedIcon = Icons.Rounded.Book,
        unselectedIcon = Icons.Rounded.Book,
    ),
    BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Rounded.Person,
        unselectedIcon = Icons.Rounded.Person,
    )
)