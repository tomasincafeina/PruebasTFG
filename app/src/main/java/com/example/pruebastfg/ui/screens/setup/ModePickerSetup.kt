package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.R


//@Composable
//fun ModePickerSetup(navController: NavHostController) {
//    var isAssistedMode by remember { mutableStateOf(true) }
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceEvenly,
//    )
//    {
//        Box(
//            modifier = Modifier.weight(0.4f),
//        ) {
//            Text(
//                "Elige como quieres usar EasyUi",
//                fontSize = 35.sp,
//                modifier = Modifier.padding(20.dp),
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.SemiBold,
//                lineHeight = 40.sp
//            )
//        }
//        Column(
//            modifier = Modifier
//                .weight(1.5f)
//                .fillMaxSize()
//        ) {
//            Text("Asistido", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
//            Card(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .padding(15.dp)
//            ) {
//                //Spacer(modifier = Modifier.width(30.dp))
//                Column(
//                    verticalArrangement = Arrangement.SpaceAround,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    modifier = Modifier
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.Info,
//                            contentDescription = "info",
//                            tint = Color.DarkGray,
//                            modifier = Modifier
//                        )
//                        Text(
//                            "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
//                            fontSize = 15.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                        )
//                    }
//
//                }
//            }
//
//            Card(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .padding(15.dp)
//            ) {
//                Text("Individual", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
//
//                Column(
//                    modifier = Modifier
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.Info,
//                        contentDescription = "info",
//                        tint = Color.DarkGray,
//                        modifier = Modifier
//                    )
//                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
//                        Text(
//                            "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
//                            fontSize = 15.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
@Composable
fun ModePickerSetup(
    navController: NavHostController,
    setToIndividualMode: () -> Unit,
    setToAssistedMode: () -> Unit
) {
    //var isAssistedMode by remember { mutableStateOf(true) }
    var selectedMode by remember { mutableStateOf<String?>("assisted") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

    ) {
        Text(
            stringResource(R.string.uso_easyui),
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        // Opción Asistido
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            ModeOptionCard(
                title = stringResource(R.string.asistido),
                description = "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
                isSelected = selectedMode == "assisted",
                onSelect = {
                    setToAssistedMode()
                    selectedMode = "assisted"
                },
                onInfoClick = { /* Mostrar diálogo con más info */ }
            )

            // Opción Individual
            ModeOptionCard(
                title = stringResource(R.string.individual),
                description = "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
                isSelected = selectedMode == "individual",
                onSelect = {
                    setToIndividualMode()
                    selectedMode = "individual"
                },
                onInfoClick = { /* Mostrar diálogo con más info */ }
            )

//            if (selectedMode != null) {
//                Button(onClick = { /* Navegar */ }, modifier = Modifier.padding(16.dp)) {
//                    Text("Continuar con modo ${selectedMode}")
//                }
//            }
        }
    }
}

@Composable
fun ModeOptionCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onInfoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(5.dp)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onSelect
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onInfoClick) {
                    Icon(Icons.Outlined.Info, contentDescription = stringResource(R.string.mas_informacion))
                }
            }
            Text(description, modifier = Modifier.padding(top = 8.dp))
        }
    }
}