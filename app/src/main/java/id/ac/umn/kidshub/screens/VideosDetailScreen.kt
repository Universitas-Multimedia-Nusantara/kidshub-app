package id.ac.umn.kidshub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.data.home.videos.VideosDataProvider
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosDetailScreen(
    videosId: String,
    videosTitle: Array<String>,
    videosDescription: Array<String>,
    videosUrl: Array<String>,
    videosUploader: Array<String>,
) {
    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "Videos",
                onBackClick = {
                    NavigationRouter.navigateTo(Screen.HomeScreen)
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(20.dp, 25.dp, 0.dp, 0.dp),
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = null,
                    )
                }
            )
        },
        bottomBar = {
            KidsHubBottomAppBar(selectedItem = 0)
        }
    )  { paddingValues ->
        VideosDetailScreenContent(
            videosId = videosId.toInt(),
            videosTitle = videosTitle,
            videosDescription = videosDescription,
            videosUrl = videosUrl,
            videosUploader = videosUploader,
            lifeCyclerOwner = LocalLifecycleOwner.current,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun VideosDetailScreenContent(
    videosId: Int?,
    videosTitle: Array<String>,
    videosDescription: Array<String>,
    videosUrl: Array<String>,
    videosUploader: Array<String>,
    lifeCyclerOwner: LifecycleOwner,
    paddingValues: PaddingValues,
) {
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
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    factory = { context ->
                        YouTubePlayerView(context).apply {
                            lifeCyclerOwner.lifecycle.addObserver(this)
                            addYouTubePlayerListener(
                                object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.loadVideo(videosUrl[videosId!!], 0f)
                                    }
                                }
                            )
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = videosTitle[videosId!!],
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "by ${videosUploader[videosId]}",
                                color = (Color(0xFF878787)),
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal,
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Description",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = videosDescription[videosId],
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun VideosDetailScreenPreview() {
    VideosDetailScreen(
        videosId = "0",
        videosTitle = VideosDataProvider.videosDataList.map { it.title }.toTypedArray(),
        videosDescription = VideosDataProvider.videosDataList.map { it.description }.toTypedArray(),
        videosUrl = VideosDataProvider.videosDataList.map { it.url }.toTypedArray(),
        videosUploader = VideosDataProvider.videosDataList.map { it.uploader }.toTypedArray(),
    )
}
