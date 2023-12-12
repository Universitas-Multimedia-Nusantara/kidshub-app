@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package id.ac.umn.kidshub.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@Composable
fun ButtonComponent (
    text: String,
    onClick: () -> Unit,
    isEnabled : Boolean = false,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
            .height(55.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color(0xFF47A7FF),
            contentColor = Color.White
        ),
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(text,
            fontSize = 24.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

@Composable
fun TextFieldComponent (
    labelValue: String,
    onTextSelected: (String) -> Unit,
) {

    val textValue = remember {
        mutableStateOf("")
    }

    val localFocusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        placeholder = {
            Text(
                text = labelValue,
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color(0xFF4B4B4B),
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF1F8FF),
            cursorColor = Color.Black,
            disabledLabelColor = Color(0xFFF1F8FF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()
            }
        ),
        trailingIcon = {
//            if (textValue.value.isNotEmpty()) {
//                IconButton(onClick = { textValue.value = "" }) {
//                    Icon(
//                        imageVector = Icons.Outlined.Close,
//                        contentDescription = null
//                    )
//                }
//            }
        }
    )
}

@Composable
fun PasswordTextFieldComponent (
    labelValue: String,
    onTextSelected: (String) -> Unit,
) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val localFocusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        placeholder = {
            Text(
                text = labelValue,
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color(0xFF4B4B4B),
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF1F8FF),
            cursorColor = Color.Black,
            disabledLabelColor = Color(0xFFF1F8FF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()
            }
        ),
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }

            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(
                    imageVector = iconImage,
                    contentDescription = description
                )
            }
        },
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Preview
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(text = "Button", onClick = {})
}

@Preview
@Composable
fun TextFieldComponentPreview() {
    TextFieldComponent(labelValue = "TextField", onTextSelected = {})
}