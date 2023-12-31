package id.ac.umn.kidshub.navigation

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.data.home.HomeViewModel
import id.ac.umn.kidshub.data.home.videos.VideosDataProvider
import id.ac.umn.kidshub.screens.AboutScreen
import id.ac.umn.kidshub.screens.AccountCenterScreen
import id.ac.umn.kidshub.screens.LoginScreen
import id.ac.umn.kidshub.screens.MainScreen
import id.ac.umn.kidshub.screens.RegisterScreen
import id.ac.umn.kidshub.screens.HomeScreen
import id.ac.umn.kidshub.screens.BooksScreen
import id.ac.umn.kidshub.screens.HelpAndRegulationsScreen
import id.ac.umn.kidshub.screens.ProfileScreen
import id.ac.umn.kidshub.screens.VideosDetailScreen
import id.ac.umn.kidshub.screens.VideosScreen
import id.ac.umn.kidshub.ui.theme.poppinsFamily
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    homeViewModel: HomeViewModel = HomeViewModel(),
    auth: FirebaseAuth,
) {
    val userId = auth.currentUser?.uid
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit, userId) {
        coroutineScope.launch {
            homeViewModel.checkForActiveSession()

            if (homeViewModel.isUserLoggedIn.value == true) {
                try {
                    val job = async {
                        homeViewModel.getUserData()
                        homeViewModel.getVideosData()
                        homeViewModel.getBooksData()
                    }

                    job.await()

                    NavigationRouter.navigateTo(Screen.HomeScreen)
                } catch (e: Exception) {
                    Log.d("Navigation", "Error: ${e.message}")
                }
            }
        }
    }

    Crossfade(targetState = NavigationRouter.currentScreen.value, label = "") { screen ->
        when (screen) {
            is Screen.MainScreen -> MainScreen()
            is Screen.LoginScreen -> LoginScreen()
            is Screen.RegisterScreen -> RegisterScreen()
            is Screen.HomeScreen -> HomeScreen()
            is Screen.VideosScreen -> VideosScreen()
            is Screen.BooksScreen -> BooksScreen()
            is Screen.ProfileScreen -> ProfileScreen()
            is Screen.AccountCenterScreen -> AccountCenterScreen()
            is Screen.HelpAndRegulationsScreen -> HelpAndRegulationsScreen()
            is Screen.AboutScreen -> AboutScreen()
            is Screen.VideosDetailScreen -> VideosDetailScreen(
                videosId = screen.videoId,
                videosTitle = VideosDataProvider.videosDataList.map { it.title }.toTypedArray(),
                videosDescription = VideosDataProvider.videosDataList.map { it.description }.toTypedArray(),
                videosCode = VideosDataProvider.videosDataList.map { it.code }.toTypedArray(),
                videosUrl = VideosDataProvider.videosDataList.map { it.url }.toTypedArray(),
                videosUploader = VideosDataProvider.videosDataList.map { it.uploader }.toTypedArray(),
            )
        }
    }
}

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.MainScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidsHubTopAppBar(
    title: String,
    subtitle: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    onBackClick: () -> Unit = {},
) {

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,
        ),
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = onBackClick) {
                    navigationIcon()
                }
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment
                    .CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                    if (subtitle != null) {
                        Text(
                            modifier = Modifier
                                .padding(0.dp, 30.dp, 0.dp, 0.dp),
                            text = subtitle,
                            color = (Color(0xFF47A7FF)),
                            fontSize = 16.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
                Box {
                    Image(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .height(40.dp)
                            .padding(10.dp)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                Color.White,
                RoundedCornerShape(0.dp)
            ),
//        actions = {
//            actions()
//        },
    )
}

@Composable
fun KidsHubBottomAppBar(selectedItem: Int) {

    NavigationBar(
        containerColor = Color.White,
    ) {
        listOfBottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    NavigationRouter.navigateTo(
                        when (index) {
                            0 -> Screen.HomeScreen
                            1 -> Screen.VideosScreen
                            2 -> Screen.BooksScreen
                            3 -> Screen.ProfileScreen
                            else -> Screen.HomeScreen
                        }
                    )
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = bottomNavigationItem.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF47A7FF),
                    selectedTextColor = Color(0xFF47A7FF),
                    indicatorColor = Color.White,
                    unselectedIconColor = Color(0xFFAFAFAF),
                    unselectedTextColor = Color(0xFFAFAFAF),
                ),
            )
        }
    }
}

@Preview
@Composable
fun PreviewKidsHubTopAppBar() {
    KidsHubTopAppBar(
        title = "Home",
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(20.dp, 25.dp, 0.dp, 0.dp),
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
fun PreviewKidsHubBottomAppBar() {
    KidsHubBottomAppBar(selectedItem = 0)
}
