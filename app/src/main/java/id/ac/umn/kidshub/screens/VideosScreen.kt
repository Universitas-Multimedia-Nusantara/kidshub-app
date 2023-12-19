package id.ac.umn.kidshub.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
fun VideosScreen(homeViewModel: HomeViewModel = HomeViewModel()) {

    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "Planet of Video & Music",
                subtitle = "Unlock your creativity !",
            )
        },
        bottomBar = {
            KidsHubBottomAppBar(selectedItem = 1)
        }
    )  { paddingValues ->
        VideosScreenContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
fun VideosScreenContent(
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
                    items(
                        items = videos,
                        itemContent = {
                            VideosScreenListItem(videosData = it)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VideosScreenListItem(
    videosData: VideosData,
    homeViewModel: HomeViewModel = HomeViewModel()
) {

    val context = LocalContext.current

    Card(
        border = BorderStroke(5.dp, Color(0xFFDDEFFC)),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            NavigationRouter.navigateTo(Screen.VideosDetailScreen(videosData.id))
            homeViewModel.updateUserExp()
            Toast.makeText(context, "You've gained more exp!", Toast.LENGTH_SHORT).show()
        }
    ) {
        Column {
            GlideImage(
                modifier = Modifier
                    .background(
                        Color(0xFFEFEFEF),
                        RoundedCornerShape(20.dp)
                    )
                    .fillMaxSize(),
                model = videosData.thumbnail,
                contentDescription = videosData.title,
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = videosData.title,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = videosData.uploader,
                        color = Color(0xFF47A7FF),
                        fontSize = 14.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Preview
@Composable
fun VideosScreenPreview() {
    VideosScreen()
}