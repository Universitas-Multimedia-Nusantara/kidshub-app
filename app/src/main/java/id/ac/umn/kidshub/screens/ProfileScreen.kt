package id.ac.umn.kidshub.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@Composable
fun ProfileScreen() {
    Box(modifier =  Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen",
            fontFamily = poppinsFamily,
            fontSize = 22.sp
        )
    }
}