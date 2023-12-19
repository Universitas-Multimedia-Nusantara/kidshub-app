package id.ac.umn.kidshub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.data.profile.ProfileViewModel
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "About",
                onBackClick = {
                    NavigationRouter.navigateTo(Screen.ProfileScreen)
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
            KidsHubBottomAppBar(selectedItem = 3)
        }
    )  { paddingValues ->
        AboutScreenContent(
            paddingValues = paddingValues
        )
    }

}

@Composable
fun AboutScreenContent(
    profileViewModel: ProfileViewModel = ProfileViewModel(),
    paddingValues: PaddingValues
) {

    profileViewModel.userExpDifferentiate()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(30.dp, 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    content = {
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "About KidsHub App",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Welcome to KidsHub, a project developed as part of the Mobile Application Programming course (IF570-A) at Universitas Multimedia Nusantara.",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Developers",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Muhammad Naufal Syarif",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "NIM: 00000055788",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Email: muhammad.naufal3@student.umn.ac.id",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Muhammad Akbar Masadisty Putra",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "NIM: 00000062913",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Email: muhammad.akbar2@student.umn.ac.id",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Bobby Januario Ricky",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "NIM: 00000055915",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Email: bobby.januario@student.umn.ac.id",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Aloysius Jonathan",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "NIM: 00000055884",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "Email: aloysius.jonathan@student.umn.ac.id",
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black,
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Contacts Us",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "For any inquiries or feedback, feel free to reach out to our developers via email. Thank you for supporting our project!",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            ButtonComponent(
                                text = "Ok",
                                onButtonClicked = {
                                    NavigationRouter.navigateTo(Screen.ProfileScreen)
                                },
                                isEnabled = true
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AboutScreenScreenPreview() {
    AboutScreen()
}