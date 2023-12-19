package id.ac.umn.kidshub.screens

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.components.ClickableTextComponent
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
    videosCode: Array<String>,
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
            KidsHubBottomAppBar(selectedItem = 1)
        }
    )  { paddingValues ->
        VideosDetailScreenContent(
            videosId = videosId.toInt() - 1,
            videosTitle = videosTitle,
            videosDescription = videosDescription,
            videosCode = videosUrl,
            videosUrl = videosCode,
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
    videosCode: Array<String>,
    videosUrl: Array<String>,
    videosUploader: Array<String>,
    lifeCyclerOwner: LifecycleOwner,
    paddingValues: PaddingValues,
) {

    val context = LocalContext.current

    fun downloadVideo(): Long {
        val downloadManager = context.getSystemService(DownloadManager::class.java)

        try {
            val request = DownloadManager.Request(Uri.parse(videosUrl[videosId!!])).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                setTitle(videosTitle[videosId])
                setDescription("Downloading ${videosTitle[videosId]}")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${videosTitle[videosId]}.mp4")
            }
            return downloadManager.enqueue(request)
        } catch (e: Exception) {
            Log.d("Download", "Failed to download video")
            Log.d("Download", e.toString())
            Log.d("Download", videosUrl[videosId!!])
            Toast.makeText(context, "Failed to download video", Toast.LENGTH_SHORT).show()
        }
        return 0
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
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth(),
                    factory = { context ->
                        YouTubePlayerView(context).apply {
                            lifeCyclerOwner.lifecycle.addObserver(this)
                            addYouTubePlayerListener(
                                object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.loadVideo(videosCode[videosId!!], 0f)
                                    }
                                }
                            )
                        }
                    }
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color(0xFF47A7FF),
                            contentColor = Color.White
                        ),
                        onClick = {
                            downloadVideo()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download_icon),
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color(0xFF47A7FF),
                            contentColor = Color.White
                        ),
                        onClick = {
                            NavigationRouter.navigateTo(Screen.HomeScreen)
                        }
                    ) {
                        Text(
                            text = "More",
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp),
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "Title",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
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
                        modifier = Modifier.alpha(0.55f),
                        text = "Description",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
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


@Preview
@Composable
fun VideosDetailScreenPreview() {
    VideosDetailScreen(
        videosId = "0",
        videosTitle = VideosDataProvider.videosDataList.map { it.title }.toTypedArray(),
        videosDescription = VideosDataProvider.videosDataList.map { it.description }.toTypedArray(),
        videosCode = VideosDataProvider.videosDataList.map { it.code }.toTypedArray(),
        videosUrl = VideosDataProvider.videosDataList.map { it.url }.toTypedArray(),
        videosUploader = VideosDataProvider.videosDataList.map { it.uploader }.toTypedArray(),
    )
}