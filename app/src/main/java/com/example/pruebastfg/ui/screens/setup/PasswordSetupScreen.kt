package com.example.pruebastfg.ui.screens.setup

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.AppScreens
import com.example.pruebastfg.ui.SetupBottomAppBar
import com.example.pruebastfg.ui.sharedItems.BottomBarNavigation
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
    val confirmPwdFocusRequester = remember { FocusRequester() }
    val pwdIsCorrectLength: Boolean = pwd.length == 4

    var isPwdVisible by remember { mutableStateOf(false) }
    var confirPwd by remember { mutableStateOf("") }
    val arePwdEqual = pwd == confirPwd && pwdIsCorrectLength

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(R.string.establece_la_contrase_a_de_usuario_administrador),
                fontSize = 35.sp,
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 40.sp
            )

            Text(
                modifier = Modifier.width(300.dp),
                text = stringResource(R.string.digitos_contrasena),
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))

            // 🔐 First Password Field
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = pwd,
                    onValueChange = {
                        if (it.length <= 4 && it.all(Char::isDigit)) {
                            onPasswordChange(it)
                        }
                    },
                    label = { Text(text = stringResource(R.string.ingresa_tu_contrasena)) },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (pwd.isNotEmpty()) {
                                if (pwdIsCorrectLength) PwdCorrectIcon() else PwdIncorrectIcon()
                            }
                            IconButton(onClick = { isPwdVisible = !isPwdVisible }) {
                                Icon(
                                    imageVector = if (isPwdVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (isPwdVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    },
                    isError = pwd.isNotEmpty() && !pwdIsCorrectLength,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { confirmPwdFocusRequester.requestFocus() }),
                    singleLine = true,
                    visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onKeyEvent {
                            if (it.key == Key.Backspace && it.type == KeyEventType.KeyDown && pwd.isNotEmpty()) {
                                onPasswordChange(pwd.dropLast(1))
                                true
                            } else false
                        }
                )
            }

            // 🔐 Confirm Password Field
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = confirPwd,
                    onValueChange = {
                        if (it.length <= 4 && it.all(Char::isDigit)) {
                            confirPwd = it
                        }
                    },
                    label = { Text(text = stringResource(R.string.repite_tu_contrasena)) },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (confirPwd.isNotEmpty()) {
                                if (arePwdEqual) PwdCorrectIcon() else PwdIncorrectIcon()
                            }
                            IconButton(onClick = { isPwdVisible = !isPwdVisible }) {
                                Icon(
                                    imageVector = if (isPwdVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (isPwdVisible) "Hide password" else "Show password"
                                )
                            }
                        }
                    },
                    isError = confirPwd.isNotEmpty() && !arePwdEqual,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (arePwdEqual) {
                                setPassword(pwd)
                                keyboardController?.hide()
                            }
                        }
                    ),
                    singleLine = true,
                    visualTransformation = if (isPwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .focusRequester(confirmPwdFocusRequester)
                        .onKeyEvent {
                            if (it.key == Key.Backspace && it.type == KeyEventType.KeyDown && confirPwd.isNotEmpty()) {
                                confirPwd = confirPwd.dropLast(1)
                                true
                            } else false
                        }
                )
                if (confirPwd.isNotEmpty() && pwd.isNotEmpty() && !arePwdEqual && confirPwd.length == 4) {
                    Text(
                        text = "Las contraseñas no coinciden. Asegúrate de que ambas sean iguales.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }

            //Text("${pwd.length}/4 dígitos")
        }
        BottomBarNavigation(
            arePwdEqual,
            { navController.popBackStack() },
            { navController.navigate(AppScreens.FinishedSetup.name) }
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