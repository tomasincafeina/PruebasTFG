package com.example.pruebastfg.ui.screens.settings.pwd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.example.pruebastfg.ui.sharedItems.PwdCorrectIcon
import com.example.pruebastfg.ui.sharedItems.PwdIncorrectIcon

@Composable
fun PasswordSetupScreen(
    onDoneClick: (pwd: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var pwd by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val pwdIsCorrectLength: Boolean = pwd.length == 4

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.width(350.dp),
            text = "La contraseña debe tener exactamente 4 números"
        )

        // Mostramos el contador de dígitos
        Text("${pwd.length}/4 números")

        OutlinedTextField(
            value = pwd,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) {
                    pwd = it
                }
            },
            label = {
                Text(
                    text = "Ingresa tu contraseña"
                )
            },
            trailingIcon = {
                if (pwd.isEmpty()) {

                } else if (pwdIsCorrectLength) {
                    PwdCorrectIcon()
                } else {
                    PwdIncorrectIcon()

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
                        onDoneClick(pwd)
                    }
                }
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onKeyEvent {
                    if (it.key == Key.Backspace && it.type == KeyEventType.KeyDown) {
                        if (pwd.isNotEmpty()) {
                            pwd = pwd.dropLast(1)
                        }
                        true
                    } else {
                        false
                    }
                }
        )


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