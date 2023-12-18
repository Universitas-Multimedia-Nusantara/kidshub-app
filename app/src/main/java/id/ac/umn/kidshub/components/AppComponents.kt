@file:OptIn(
    ExperimentalMaterial3Api::class
)

package id.ac.umn.kidshub.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import id.ac.umn.kidshub.ui.theme.poppinsFamily

@Composable
fun ButtonComponent (
    text: String,
    onButtonClicked: () -> Unit,
    isEnabled : Boolean,
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
        onClick = {
            onButtonClicked.invoke()
        },
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
    errorStatus: Boolean = false,
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
        },
        isError = errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent (
    labelValue: String,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
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
        },
        isError = errorStatus
    )
}

@Composable
fun ClickableTextComponent(
    initialText: String,
    actionText: String,
    initialFontSize: Int = 16,
    actionFontSize: Int = 16,
    initialFontWeight: FontWeight = FontWeight.SemiBold,
    actionFontWeight: FontWeight = FontWeight.SemiBold,
    paddingTop: Int = 16,
    color: Color = Color(0xFF47A7FF),
    textAlign: TextAlign = TextAlign.Center,
    onClick: () -> Unit
){
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = initialFontWeight,
                fontSize = initialFontSize.sp,
                fontFamily = poppinsFamily,
            )
        ) {
            append(initialText)
        }
        withStyle(
            style = SpanStyle(
                color = color,
                fontWeight = actionFontWeight,
                fontSize = actionFontSize.sp,
                fontFamily = poppinsFamily,
            )
        ) {
            pushStringAnnotation(
                tag = actionText,
                annotation = actionText
            )
            append(actionText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop.dp, bottom = 16.dp),
        style = TextStyle(
            color = Color.Black,
            fontWeight = initialFontWeight,
            fontSize = initialFontSize.sp,
            fontFamily = poppinsFamily,
            textAlign = textAlign,
        ),
        text = annotatedString,
        onClick = {
            offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    onClick()
                }
        }
    )
}

@Composable
fun CustomAlertDialog(
    onDissmissRequest: () -> Unit,
    confirmButtonOnClick: () -> Unit,
    confirmButtonText: String,
    title: String,
    text: String,

) {
    AlertDialog(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp,5.dp,10.dp,10.dp),
        onDismissRequest = {
            onDissmissRequest()
        },
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    confirmButtonOnClick()
                }
            ) {
                Text(confirmButtonText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End,
                    color = Color(0xFF000000),
                )
            }
        },
        title = {
            Text(title,
                fontSize = 20.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
        },
        text = {
            Text(text,
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
        },
    )
}

@Composable
fun CustomDialogTwoButtonsCall(
    openDialogCustom: MutableState<Boolean>,
    customHeadingText: String,
    customBodyText: String,
    customButtonLeftText: String,
    customButtonRightText: String,
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        CustomDialogTwoButtons(
            openDialogCustom = openDialogCustom,
            customHeadingText = customHeadingText,
            customBodyText = customBodyText,
            customButtonLeftText = customButtonLeftText,
            customButtonRightText = customButtonRightText,
        )
    }
}

@Composable
fun CustomDialogOneButtonsCall(
    openDialogCustom: MutableState<Boolean>,
    customHeadingText: String,
    customBodyText: String,
    customButtonText: String,
    onTextButtonClicked: () -> Unit = {},
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        CustomDialogOneButtons(
            openDialogCustom = openDialogCustom,
            customHeadingText = customHeadingText,
            customBodyText = customBodyText,
            customButtonText = customButtonText,
            onTextButtonClicked = onTextButtonClicked,
        )
    }
}

@Composable
fun CustomDialogTwoButtons(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    customHeadingText: String,
    customBodyText: String,
    customButtonLeftText: String,
    customButtonRightText: String,
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
            ) {
            Column(
                modifier
                    .background(Color.White)) {

                //.......................................................................
    //            Image(
    //                painter = painterResource(id = R.drawable.notification),
    //                contentDescription = null, // decorative
    //                contentScale = ContentScale.Fit,
    //                colorFilter  = ColorFilter.tint(
    //                    color = Purple40
    //                ),
    //                modifier = Modifier
    //                    .padding(top = 35.dp)
    //                    .height(70.dp)
    //                    .fillMaxWidth(),
    //
    //                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = customHeadingText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = customBodyText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                //.......................................................................
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Color(0xFFDDEFFC)),
                    horizontalArrangement = Arrangement.SpaceAround) {

                    TextButton(onClick = {
                        openDialogCustom.value = false

                    }) {

                        Text(
                            customButtonLeftText,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                    TextButton(onClick = {
                        openDialogCustom.value = false
                    }) {
                        Text(
                            customButtonRightText,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
}

@Composable
fun CustomDialogOneButtons(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    customHeadingText: String,
    customBodyText: String,
    customButtonText: String,
    onTextButtonClicked: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
    ) {
        Column(
            modifier
                .background(Color.White)) {

            //.......................................................................
            //            Image(
            //                painter = painterResource(id = R.drawable.notification),
            //                contentDescription = null, // decorative
            //                contentScale = ContentScale.Fit,
            //                colorFilter  = ColorFilter.tint(
            //                    color = Purple40
            //                ),
            //                modifier = Modifier
            //                    .padding(top = 35.dp)
            //                    .height(70.dp)
            //                    .fillMaxWidth(),
            //
            //                )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = customHeadingText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = customBodyText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color(0xFFDDEFFC)),
                horizontalArrangement = Arrangement.SpaceAround) {

                TextButton(onClick = {
                    openDialogCustom.value = false
                    onTextButtonClicked()
                }) {

                    Text(
                        customButtonText,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(text = "Button", onButtonClicked = {}, isEnabled = true)
}

@Preview
@Composable
fun TextFieldComponentPreview() {
    TextFieldComponent(labelValue = "TextField", onTextSelected = {})
}

@SuppressLint("UnrememberedMutableState")
@Preview (name="Custom Dialog")
@Composable
fun MyDialogUIPreview1(){
    CustomDialogTwoButtons(
        openDialogCustom = mutableStateOf(false),
        customHeadingText = "Heading",
        customBodyText = "Body",
        customButtonLeftText = "Left",
        customButtonRightText = "Right"
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview (name="Custom Dialog")
@Composable
fun MyDialogUIPreview2(){
    CustomDialogOneButtons(
        openDialogCustom = mutableStateOf(false),
        customHeadingText = "Heading",
        customBodyText = "Body",
        customButtonText = "Ok",
    )
}