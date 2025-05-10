package com.example.pruebastfg.ui.screens.settings.pwd

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.LockReset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.sharedItems.PwdIncorrectIcon
import kotlinx.coroutines.delay

@Composable
fun PasswordScreen(
    onDoneClick: (hideKeyboard: () -> Unit) -> Unit,
    actualPwd: String,
    goToResetPwd: () -> Unit,
) {
    var pwd by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val pwdIsCorrect = pwd == actualPwd
    val pwdIsCorrectLength: Boolean = pwd.length == 4

    var isPwdVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    // Efecto para manejar la navegación y cerrar el teclado
    LaunchedEffect(pwd) {
        if (pwdIsCorrectLength && pwdIsCorrect) {
            // 1. Primero cerramos el teclado
            keyboardController?.hide()
            // 2. Pequeño delay para asegurar que el teclado se ocultó
            //delay(100)
            // 3. Luego navegamos
            onDoneClick { keyboardController?.hide() }
        }
    }
    //DOCUMENTACION
    //el teclado al acertar la contraseña se cierra y pasa a la otra pantalla,
    // la cosa es que le cuesta 100ms cerrarse y ce superpone en la siguiente pantalla
    //el delay queda feo por eso la soluciones del keyboardController?.hide() es mejor
//    LaunchedEffect(pwd) {
//        if (pwdIsCorrectLength && pwdIsCorrect) {
//            // 1. Primero cerramos el teclado
//            keyboardController?.hide()
//            // 2. Pequeño delay para asegurar que el teclado se ocultó
//            delay(100)
//            // 3. Luego navegamos
//            onDoneClick()
//        }
//    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            modifier = Modifier.width(400.dp),
            text = "Escribe tu contraseña de administrador",
            fontSize = 40.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp,

            )
        Spacer(modifier = Modifier.height(50.dp))


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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!pwdIsCorrect && pwdIsCorrectLength) {
                        PwdIncorrectIcon()
                    }
                    IconButton(onClick = { isPwdVisible = !isPwdVisible }) {
                        Icon(
                            imageVector = if (isPwdVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (isPwdVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            isError = pwd.isNotEmpty() && pwdIsCorrectLength && !pwdIsCorrect,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (pwdIsCorrect) {
                        onDoneClick { keyboardController?.hide() }
                    }
                }
            ),
            singleLine = true,
            visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
        // Mostramos el contador de dígitos
//        Text("${pwd.length}/4 dígitos")
        // Si la contraseña es incorrecta, mostramos un mensaje de error
        if (pwd.isNotEmpty() && pwdIsCorrectLength && !pwdIsCorrect) {
            Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.contrasena_incorrecta),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    "Si has olvidado la contraseña restablecela",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Card(
                    shape = Shapes().large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = goToResetPwd,
                    modifier = Modifier
                        .width(350.dp)
                        .padding(10.dp)
                        .padding(bottom = 10.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
                ){
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Rounded.LockReset,
                            contentDescription = "Reset Password",
                            modifier = Modifier.size(35.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Restablecer contraseña",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
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