package id.ac.umn.kidshub.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.components.ClickableTextComponent
import id.ac.umn.kidshub.components.PasswordTextFieldComponent
import id.ac.umn.kidshub.components.TextFieldComponent
import id.ac.umn.kidshub.data.signup.SignupViewModel
import id.ac.umn.kidshub.data.signup.SignupUIEvent
import id.ac.umn.kidshub.navigation.NavigationRouter
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(signupViewModel: SignupViewModel = viewModel()) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
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
                    .padding(horizontal = 48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Register now",
                    fontSize = 32.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
                Text("Create your free acccount!",
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(16.dp))
                TextFieldComponent(
                    labelValue = "First Name",
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.firstNameError
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextFieldComponent(
                    labelValue = "Last Name",
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.lastNameError
                )
                Spacer(modifier = Modifier.padding(8.dp))
                TextFieldComponent(
                    labelValue = "Email",
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.emailError
                )
                Spacer(modifier = Modifier.padding(8.dp))
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.passwordError
                )
                Spacer(modifier = Modifier.padding(16.dp))
                ButtonComponent(
                    text = "Register",
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationPassed.value
                )
                ClickableTextComponent(
                    initialText = "Already have an account? ",
                    actionText = "Login here!",
                    onClick = {
                        NavigationRouter.navigateTo(Screen.LoginScreen)
                        Log.d("Navigating to", "${NavigationRouter.navigateTo(Screen.LoginScreen)}")
                    }
                )
            }
        }
        if (signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp),
                color = Color(0xFFAFAFAF),
                trackColor = Color(0xFF47A7FF),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    RegisterScreen()
}