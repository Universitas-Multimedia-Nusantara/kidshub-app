package id.ac.umn.kidshub.screens

import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.components.ClickableTextComponent
import id.ac.umn.kidshub.data.profile.ProfileViewModel
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(
        bottomBar = {
            KidsHubBottomAppBar(selectedItem = 2)
        }
    )  { paddingValues ->
        ProfileScreenContent(
            paddingValues = paddingValues
        )
    }

}

@Composable
fun ProfileScreenContent(
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
                    .fillMaxSize()
                    .padding(30.dp, 75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .background(
                            Color(0xFFE5E5E5),
                            RoundedCornerShape(100.dp)
                        ),
                    contentAlignment = Alignment.Center,

                ) {
                    Text(
                        text = "${profileViewModel.usersData.firstName[0]}${profileViewModel.usersData.lastName[0]}",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${profileViewModel.usersData.firstName} ${profileViewModel.usersData.lastName}",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${profileViewModel.userExp.value}",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF47A7FF)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "Account",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                    ClickableTextComponent(
                        initialText = "",
                        actionText = "Account Center",
                        actionFontSize = 20,
                        actionFontWeight = FontWeight.Medium,
                        paddingTop = 25,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        onClick = {
                            //
                        }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "Information & Support",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                    ClickableTextComponent(
                        initialText = "",
                        actionText = "Help",
                        actionFontSize = 20,
                        actionFontWeight = FontWeight.Medium,
                        paddingTop = 25,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        onClick = {
                            //
                        }
                    )
                    ClickableTextComponent(
                        initialText = "",
                        actionText = "About",
                        actionFontSize = 20,
                        actionFontWeight = FontWeight.Medium,
                        paddingTop = 60,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        onClick = {
                            //
                        }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "Login",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                    ClickableTextComponent(
                        initialText = "",
                        actionText = "Sign out",
                        actionFontSize = 20,
                        actionFontWeight = FontWeight.Medium,
                        paddingTop = 25,
                        color = Color(0xFFFF5353),
                        textAlign = TextAlign.Start,
                        onClick = {
                            profileViewModel.logout()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}