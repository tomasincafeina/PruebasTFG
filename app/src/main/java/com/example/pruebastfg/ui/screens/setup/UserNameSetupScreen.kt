package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UserNameSetupScreen(
    navController: NavHostController,
    userName: String,
    onNameChange: (String) -> Unit,
    btnSaveOnClick: () -> Unit = {},
    btnClearOnClick: () -> Unit = {},
    btnNextOnClick: () -> Unit = {},
    setupStatus: Boolean

) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = setupStatus.toString())
        OutlinedTextField(
            value = userName,
            onValueChange = { onNameChange(it) },
            label = { Text("Escribe tu nombre") }
        )

        Row {
            Button(
                onClick = btnSaveOnClick
            ) {
                Text("Guardar nombre")
            }
            Button(
                onClick = btnClearOnClick
            ) {
                Text("Borrar nombre")
            }
        }
        Button(
            onClick = btnNextOnClick
        ) {
            Text("Siguiente")

        }
    }
}