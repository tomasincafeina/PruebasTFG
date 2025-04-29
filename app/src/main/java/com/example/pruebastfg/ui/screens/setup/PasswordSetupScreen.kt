package com.example.pruebastfg.ui.screens.setup

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.pruebastfg.ui.sharedItems.PwdCorrectIcon
import com.example.pruebastfg.ui.sharedItems.PwdIncorrectIcon

@Composable
fun PasswordSetupScreen(
    navController: NavHostController,
    pwd: String,
    onPasswordChange: (String) -> Unit,
    setPassword: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val pwdIsCorrectLength: Boolean = pwd.length == 4
    var isPwdVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Establece la contraseña de usuario administrador",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        Text(
            modifier = Modifier.width(300.dp),
            text = "La contraseña debe tener exactamente 4 dígitos numéricos",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = pwd,
                onValueChange = { it ->
                    // Only allow numeric input and limit to 4 characters
                    if (it.length <= 4 && it.all { it.isDigit() }) {
                        onPasswordChange(it)
                    }
                },
                label = {
                    Text(
                        text = "Ingresa tu contraseña"
                    )
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        // Validation icons (shown to the left of the visibility toggle)
                        if (pwd.isNotEmpty()) {
                            if (pwdIsCorrectLength) {
                                PwdCorrectIcon()
                            } else {
                                PwdIncorrectIcon()
                            }
                        }
                        // Password visibility toggle icon
                        IconButton(
                            onClick = { isPwdVisible = !isPwdVisible },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = if (isPwdVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (isPwdVisible) "Hide password" else "Show password",
                                //modifier = Modifier.size(48.dp)
                            )
                        }
                    }

                },
                isError = pwd.isNotEmpty() && !pwdIsCorrectLength,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (pwdIsCorrectLength) {
                            setPassword(pwd)
                            keyboardController?.hide()
                        }
                    }
                ),
                singleLine = true,
                visualTransformation = if (isPwdVisible) {
                    VisualTransformation.None // Show password as plain text
                } else {
                    PasswordVisualTransformation() // Hide password (show dots)
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onKeyEvent {
                        if (it.key == Key.Backspace && it.type == KeyEventType.KeyDown) {
                            if (pwd.isNotEmpty()) {
                                onPasswordChange(pwd.dropLast(1))
                            }
                            true
                        } else {
                            false
                        }
                    }
            )


        }

        // Mostramos el contador de dígitos
        Text("${pwd.length}/4 dígitos")

        Button(
            onClick = {
                setPassword(pwd)
                keyboardController?.hide()
                focusRequester.freeFocus()
            },
            enabled = pwdIsCorrectLength,
            elevation = ButtonDefaults.buttonElevation(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = Color.Black,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text("Guardar contraseña", modifier = Modifier.padding(10.dp), fontSize = 20.sp)
        }
    }
}


//NO LO USO PQ ES MEJOR USAR EL TECLADO INTERNO DEL DISPOSITIVO PARA INTRODUCIR LA CONTRASEÑA
//val numberList = listOf(
//    1, 2, 3, 4, 5, 6, 7, 8, 9
//)
//
//@Composable
//fun PasswordScreen(onDoneClick: (pwd : String) -> Unit, actualPwd : String) {
//    var pwd by remember { mutableStateOf("") }
//
//    Column(
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = actualPwd)
//
//        OutlinedTextField(
//            value = pwd,
//            onValueChange = { pwd = it }
//        )
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier.width(300.dp)
//        ) {
//            items(numberList) { number ->
//                NumberItem(
//                    number = number,
//                    onClick = { pwd = "$pwd$number" }
//                )
//            }
//            // Fila inferior: Borrar (⌫), 0, Correcto (✓)
//            item {
//                PwdOptItem (
//                    icon = Icons.Rounded.ArrowBack,
//                    onClick = {pwd = pwd.dropLast(1) }
//                )
//            }
//            item {
//                NumberItem(
//                    number = 0,
//                    onClick = { pwd += "0" }
//                )
//            }
//            item {
//                PwdOptItem (
//                    icon = Icons.Rounded.Done,
//                    onClick = { onDoneClick(pwd) }
//                )
//            }
//        }
//    }
//}