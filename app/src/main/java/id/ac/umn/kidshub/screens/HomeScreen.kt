package id.ac.umn.kidshub.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.data.home.HomeViewModel
import id.ac.umn.kidshub.data.home.videos.VideosData
import id.ac.umn.kidshub.data.home.videos.VideosDataProvider
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = HomeViewModel()) {

    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = homeViewModel.greeting,
                subtitle = "${homeViewModel.userData.firstName} ${homeViewModel.userData.lastName}",
            )
        },
        bottomBar = {
            KidsHubBottomAppBar(selectedItem = 0)
        }
    )  { paddingValues ->
        HomeScreenContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = HomeViewModel()
) {

    val videos = remember {
        VideosDataProvider.videosDataList
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(30.dp, 30.dp),
            ) {
                LazyColumn() {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(157.dp)
                                .background(
                                    Color(0xFFDDEFFC),
                                    RoundedCornerShape(20.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box () {
                                    Column(
                                    ) {
                                        Text(
                                            "Children's",
                                            fontSize = 16.sp,
                                            fontFamily = poppinsFamily,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Start,
                                            color = Color.Black,
                                        )
                                        Text(
                                            "Education",
                                            fontSize = 26.sp,
                                            fontFamily = poppinsFamily,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                            color = Color.Black,
                                        )
                                        Column(
                                            modifier = Modifier
                                                .padding(0.dp,10.dp, 0.dp, 0.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Button(onClick = { /*TODO*/ },
                                                modifier = Modifier
                                                    .height(32.dp),
                                                colors = ButtonDefaults.elevatedButtonColors(
                                                    containerColor = Color(0xFF47A7FF),
                                                    contentColor = Color.White
                                                ),
                                            ) {
                                                Text("Learn more",
                                                    color = Color.White,
                                                    fontSize = 12.sp,
                                                    fontFamily = poppinsFamily,
                                                    fontWeight = FontWeight.SemiBold,
                                                )
                                            }
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .width(118.15.dp)
                                            .height(138.dp),
                                        painter = painterResource(id = R.drawable.ic_hero_home_screen),
                                        contentDescription = stringResource(id = R.string.kid_content_description),
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(15.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        Color(0xFFFCDDDD),
                                        RoundedCornerShape(15.dp)
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(20.dp),
                                ) {
                                    Text(
                                        text = "Playground of",
                                        color = Color.Black,
                                        fontSize = 10.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Text(
                                        text = "Knowledge",
                                        fontSize = 16.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Column(
                                        Modifier
                                            .fillMaxWidth()
                                            .absolutePadding(0.dp, 10.dp, 0.dp, 0.dp)
                                    ) {
                                        Button(onClick = {
                                            NavigationRouter.navigateTo(Screen.BooksScreen)
                                        },
                                            colors = ButtonDefaults.elevatedButtonColors(
                                                containerColor = Color(0xFFFF4747),
                                                contentColor = Color.White
                                            ),
                                            modifier = Modifier
                                                .height(30.dp)
                                        ) {
                                            Text("Go !",
                                                color = Color.White,
                                                fontSize = 8.sp
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .width(15.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        Color(0xFFDEDDFC),
                                        RoundedCornerShape(15.dp)
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(20.dp),
                                ) {
                                    Text(
                                        text = "Planet of",
                                        color = Color.Black,
                                        fontSize = 10.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Text(
                                        text = "Video & Music",
                                        fontSize = 16.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Column(
                                        Modifier
                                            .fillMaxWidth()
                                            .absolutePadding(0.dp, 10.dp, 0.dp, 0.dp)
                                    ) {
                                        Button(onClick = {
                                            NavigationRouter.navigateTo(Screen.VideosScreen)
                                        },
                                            colors = ButtonDefaults.elevatedButtonColors(
                                                containerColor = Color(0xFF4B47FF),
                                                contentColor = Color.White
                                            ),
                                            modifier = Modifier
                                                .height(30.dp)
                                        ) {
                                            Text(
                                                "Watch",
                                                color = Color.White,
                                                fontSize = 8.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(30.dp)
                        )
                        Box(
                        ) {
                            Text(
                                "Videos of the week ✨",
                                color = Color(0xFF371B34),
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )
                    }
                    items(
                        items = videos.take(10),
                        itemContent = {
                            VideosListItem(videosData = it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VideosListItem(
    videosData: VideosData,
    homeViewModel: HomeViewModel = HomeViewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xFF47A7FF),
                    contentColor = Color.White
                ),
                onClick = {
                    NavigationRouter.navigateTo(Screen.VideosDetailScreen(videosData.id))
                    homeViewModel.updateUserExp()
                    Toast.makeText(context, "You've gained more exp!", Toast.LENGTH_SHORT).show()

                    Log.d("VideosListItem", "onClick: ${videosData.id}")
                }
            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.ic_video_play_button),
                    contentDescription = videosData.description,
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp, 7.dp, 0.dp, 0.dp),
                ) {
                    Text(
                        text = videosData.title,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = videosData.description,
                        color = Color(0xFF878787),
                        fontSize = 12.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
