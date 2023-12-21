package id.ac.umn.kidshub.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.R
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.components.ClickableTextComponent
import id.ac.umn.kidshub.components.CustomDialogOneButtonsCall
//import id.ac.umn.kidshub.components.CustomDialogOneButtonsCall
import id.ac.umn.kidshub.components.PasswordTextFieldComponent
import id.ac.umn.kidshub.components.TextFieldComponent
import id.ac.umn.kidshub.data.login.LoginUIEvent
import id.ac.umn.kidshub.data.login.LoginViewModel
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = LoginViewModel()) {

    val dialogStateForLoginStatus = remember { mutableStateOf(false) }

    val dialogStateForLoginValidationPassed = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Login here",
                    fontSize = 32.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
                Text("Welcome back you've been missed!",
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
                Image(
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 30.dp)
                        .width(200.dp)
                        .height(200.dp),
                    painter = painterResource(id = R.drawable.ic_mainscreen_kids),
                    contentDescription = stringResource(id = R.string.kid_content_description),
                )
                TextFieldComponent(
                    labelValue = "Email",
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )
                Spacer(modifier = Modifier.padding(8.dp))
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text("Forgot password?",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                        color = Color(0xFF000000),
                    )
                }
                ButtonComponent(
                    text = "Sign In",
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = true
                )
                ClickableTextComponent(
                    initialText = "Don't have an account? ",
                    actionText = "Register here!",
                    onClick = {
                        NavigationRouter.navigateTo(Screen.RegisterScreen)
                        Log.d("Navigating to", "${NavigationRouter.navigateTo(Screen.RegisterScreen)}")
                    }
                )
            }
        }

        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp),
                color = Color(0xFFAFAFAF),
                trackColor = Color(0xFF47A7FF),
            )
        }

        loginViewModel.loginStatus.observeAsState().value?.let {
            if (!it) {
                CustomDialogOneButtonsCall(
                    openDialogCustom = dialogStateForLoginStatus,
                    customHeadingText = "Login Failed",
                    customBodyText = "Email or password is incorrect",
                    customButtonText = "OK",
                    onTextButtonClicked = {
                        loginViewModel.loginStatus.value = null
                    }
                )
            }
        }

        loginViewModel.loginValidationPassed.observeAsState().value?.let {
            if (!it) {
                CustomDialogOneButtonsCall(
                    openDialogCustom = dialogStateForLoginValidationPassed,
                    customHeadingText = "Login Failed",
                    customBodyText = "Email or password cannot be empty",
                    customButtonText = "OK",
                    onTextButtonClicked = {
                        loginViewModel.loginValidationPassed.value = null
                    }
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}