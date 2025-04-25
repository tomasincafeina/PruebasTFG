package com.example.pruebastfg.ui.screens.setup

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
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.pruebastfg.ui.sharedItems.PwdCorrectIcon
import com.example.pruebastfg.ui.sharedItems.PwdIncorrectIcon

@Composable
fun UserNameSetupScreen(
    //navController: NavHostController,
    userName: String,
    onNameChange: (String) -> Unit,
    btnSaveOnClick: () -> Unit = {},
    btnClearOnClick: () -> Unit = {},
    btnNextOnClick: () -> Unit = {},
    setupStatus: Boolean
) {
    // Focus requester to request focus for the text field
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // LaunchedEffect to request focus and show keyboard when the screen appears
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Icon(
            Icons.Rounded.Person,
            contentDescription = "persona",
            modifier = Modifier.size(100.dp)
        )

        Text(
            "¿Como te llamas?",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = userName,
            onValueChange = { onNameChange(it)  },
            label = { Text("Escribe tu nombre") },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        keyboardController?.show()
                    }
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

//        Row {
//            Button(
//                onClick = { btnSaveOnClick }
//            ) {
//                Text("Guardar nombre")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(
//                onClick = btnClearOnClick
//            ) {
//                Text("Borrar nombre")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = btnNextOnClick,
//        ) {
//            Text("Siguiente")
//        }
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