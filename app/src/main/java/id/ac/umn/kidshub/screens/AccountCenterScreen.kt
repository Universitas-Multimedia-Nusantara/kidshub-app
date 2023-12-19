package id.ac.umn.kidshub.screens

import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.components.ClickableTextComponent
import id.ac.umn.kidshub.components.TextFieldComponent
import id.ac.umn.kidshub.data.profile.ProfileUIEvent
import id.ac.umn.kidshub.data.profile.ProfileViewModel
import id.ac.umn.kidshub.data.signup.SignupUIEvent
import id.ac.umn.kidshub.navigation.KidsHubBottomAppBar
import id.ac.umn.kidshub.navigation.KidsHubTopAppBar
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCenterScreen() {
    Scaffold(
        topBar = {
            KidsHubTopAppBar(
                title = "Account Center",
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
            KidsHubBottomAppBar(selectedItem = 2)
        }
    )  { paddingValues ->
        AccountCenterScreenContent(
            paddingValues = paddingValues
        )
    }

}

@Composable
fun AccountCenterScreenContent(
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
                    .padding(30.dp, 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "First Name",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                    TextFieldComponent(
                        labelValue = profileViewModel.usersData.firstName,
                        paddingTop = 25,
                        onTextSelected = {
                            profileViewModel.onEvent(ProfileUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = profileViewModel.profileUIState.value.firstNameError
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.alpha(0.55f),
                        text = "Last Name",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                    TextFieldComponent(
                        labelValue = profileViewModel.usersData.lastName,
                        paddingTop = 25,
                        onTextSelected = {
                            profileViewModel.onEvent(ProfileUIEvent.LastNameChanged(it))
                        },
                        errorStatus = profileViewModel.profileUIState.value.lastNameError
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ButtonComponent(
                        text = "Save",
                        onButtonClicked = {
                            profileViewModel.onEvent(ProfileUIEvent.UpdateUserButtonClicked)                       },
                        isEnabled = true
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AccountCenterScreenContentPreview() {
    AccountCenterScreen()
}