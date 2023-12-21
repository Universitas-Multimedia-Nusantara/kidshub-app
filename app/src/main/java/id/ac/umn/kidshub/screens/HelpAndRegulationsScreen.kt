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
fun HelpAndRegulationsScreen() {
    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "Help & Regulations",
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
        HelpAndRegulationsScreenContent(
            paddingValues = paddingValues
        )
    }

}

@Composable
fun HelpAndRegulationsScreenContent(
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
                               text = "KidsHub App Regulations",
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
                               text = "Welcome to KidsHub, the learning application designed specifically for children! Before you start using this application, please take note of the following regulations:",
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
                               text = "1. Application Content",
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
                               text = "a. Children's Videos, Books, and Music:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- All content provided in KidsHub has been carefully selected to ensure safety and suitability for children.\n" +
                                       "- Videos, books, and music in this application are intended to support children's learning and entertainment.",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 16.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Spacer(modifier = Modifier.height(12.dp))
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "b. Content Selection:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- Content is curated carefully by our team to ensure that only age-appropriate material is available.",
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
                               text = "2. Security And Privacy",
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
                               text = "a. Children's Data:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- We respect the privacy of children. No personal information is collected from children through this application without parental or guardian consent.",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 16.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Spacer(modifier = Modifier.height(12.dp))
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "b. Parental Supervision:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- We encourage parents or guardians to be involved in their children's use of the application and provide necessary supervision.",
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
                               text = "3. Navigation and Interaction",
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
                               text = "a. \"Help\" Button:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- Use the \"Help\" button to access guidelines and information related to the application regulations.",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 16.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Spacer(modifier = Modifier.height(12.dp))
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "b. Inappropriate Actions:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- It is strictly prohibited to share or access inappropriate or harmful material through this application.",
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
                               text = "4. Technical Support",
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
                               text = "a. Questions and Technical Issues:",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.SemiBold,
                               fontSize = 18.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "- If you experience technical issues or have questions regarding the application, contact our technical support through the available options in the application.",
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
                               text = "5. Regulation Updates",
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
                               text = "We reserve the right to update these regulations. Please make sure to periodically check for regulation updates to stay compliant with the latest terms.",
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
                               text = "Contacts",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Bold,
                               fontSize = 20.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "muhammad.naufal3@student.umn.ac.id",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 14.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "muhammad.akbarl2@student.umn.ac.id",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 14.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "bobby.januario@student.umn.ac.id",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 14.sp,
                               textAlign = TextAlign.Start,
                               color = Color.Black,
                           )
                           Text(
                               modifier = Modifier
                                   .fillMaxWidth(),
                               text = "aloysius.jonathan@student.umn.ac.id",
                               fontFamily = poppinsFamily,
                               fontWeight = FontWeight.Normal,
                               fontSize = 14.sp,
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
fun HelpAndRegulationsScreenPreview() {
    HelpAndRegulationsScreen()
}