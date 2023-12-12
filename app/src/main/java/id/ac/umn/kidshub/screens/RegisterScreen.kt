package id.ac.umn.kidshub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.navigation.NavController
import id.ac.umn.kidshub.components.ButtonComponent
import id.ac.umn.kidshub.components.PasswordTextFieldComponent
import id.ac.umn.kidshub.components.TextFieldComponent
import id.ac.umn.kidshub.navigation.Screen
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

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
            labelValue = "Username",
            onTextSelected = {
                /* TODO */
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextFieldComponent(
            labelValue = "Email",
            onTextSelected = {
                /* TODO */
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordTextFieldComponent(
            labelValue = "Password",
            onTextSelected = {
                /* TODO */
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordTextFieldComponent(
            labelValue = "Confirm Password",
            onTextSelected = {
                /* TODO */
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        ButtonComponent(
            text = "Register",
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
        )
        TextButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                 navController.navigate(Screen.LoginScreen.route)
            }
        ) {
            Text("Already have an account? Login here!",
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}