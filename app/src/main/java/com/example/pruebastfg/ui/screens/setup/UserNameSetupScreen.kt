package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.R

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
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Icon(Icons.Rounded.Person, contentDescription = "person", modifier = Modifier.size(100.dp))
        //Text(text = setupStatus.toString())
        Text(
            stringResource(R.string.como_te_llamas),
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedTextField(
            value = userName,
            onValueChange = { onNameChange(it) },
            label = { Text(stringResource(R.string.escribe_tu_nombre)) },
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
            onClick = btnNextOnClick,
        ) {
            Text("Siguiente")

        }
    }
}

