package com.example.banquemisrlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisrlogin.ui.theme.BanqueMisrLoginTheme
import com.example.banquemisrlogin.ui.theme.LightRed
import com.example.banquemisrlogin.ui.theme.Red


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrLoginTheme {
                LanguageSwitcher()
            }
        }
    }
}

@Composable
fun LanguageSwitcher() {
    val context = LocalContext.current
    val configuration = context.resources.configuration
    val deviceLanguage = configuration.locales.get(0).language

    val buttonAlignment = if (deviceLanguage == "ar") Alignment.TopStart else Alignment.TopEnd
    val buttonText = if (deviceLanguage == "ar") "EN" else "العربية"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bm_icon),
            contentDescription = null,
            modifier = Modifier
                .align(if (deviceLanguage == "ar") Alignment.TopEnd else Alignment.TopStart)
                .padding(16.dp)
        )
        
        TextButton(
            onClick = {},
            modifier = Modifier
                .align(buttonAlignment)
                .padding(16.dp)
        ) {
            Text(
                text = buttonText,
                color = LightRed,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }

        UserInputFields(deviceLanguage)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputFields(language: String) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 200.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = stringResource(id = if (language == "en") R.string.username else R.string.username), color = Color.LightGray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = if (language == "en") R.string.password else R.string.password), color = Color.LightGray) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) {
                            painterResource(id = R.drawable.ic_visibility_on)
                        } else {
                            painterResource(id = R.drawable.ic_visibility_off)
                        }

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = image, contentDescription = null)
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = if (language == "en") Arrangement.Start else Arrangement.End
                ) {
                    TextButton(onClick = {}) {
                        Text(
                            text = stringResource(id = if (language == "en") R.string.forget_username_password else R.string.forget_username_password),
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val isLoginEnabled = username.isNotBlank() && password.isNotBlank()
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLoginEnabled) Red else LightRed
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = stringResource(id = if (language == "en") R.string.login else R.string.login),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = if (language == "en") Arrangement.Start else Arrangement.End
                ) {
                    Column(
                        horizontalAlignment = if (language == "en") Alignment.Start else Alignment.End
                    ) {
                        TextButton(onClick = {}) {
                            Text(
                                text = buildAnnotatedString {
                                    val needHelpText = stringResource(id = if (language == "en") R.string.need_help else R.string.need_help)
                                    val contactUsText = stringResource(id = if (language == "en") R.string.contact_us else R.string.contact_us)

                                    withStyle(style = SpanStyle(color = Color.Black)) {
                                        append(needHelpText)
                                    }

                                    append(" ")

                                    withStyle(style = SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline)) {
                                        append(contactUsText)
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            color = Color.LightGray,
                            thickness = 2.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ImageRowWithCaptions()
                    }
                }
            }
        }
    }
}



@Composable
fun ImageRowWithCaptions() {
    val imageIds = listOf(R.drawable.our_products, R.drawable.exchange_rate, R.drawable.security_tips, R.drawable.nearest_branch_or_atm)
    val captions = listOf(
        stringResource(id = R.string.our_products),
        stringResource(id = R.string.exchange_rate),
        stringResource(id = R.string.security_tips),
        stringResource(id = R.string.nearest_branch_or_atm)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageIds.zip(captions).forEach { (imageId, caption) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = caption,
                    modifier = Modifier
                        .size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = caption,
                    style = TextStyle(fontSize = 10.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(locale = "ar")
@Composable
fun LanguageSwitcherPreview() {
    BanqueMisrLoginTheme {
        LanguageSwitcher()
    }
}

@Preview
@Composable
fun UserInputFieldsPreview() {
    BanqueMisrLoginTheme {
        UserInputFields(language = "en")
    }
}
