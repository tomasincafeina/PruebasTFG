package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ModePickerSetup(){
    var isAssistedMode = true

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    )
    {
        Text(
            "Elige como quieres usar EasyUi",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 20.dp)

                ) {
                    RadioButton(
                        selected = isAssistedMode, onClick = { isAssistedMode = true  }, colors = RadioButtonColors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = Color.Black,
                            disabledUnselectedColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.width(30.dp))

                    Text("Asistido", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "info",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(9.dp)
                            .align(Alignment.Top)
                    )
                    Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
                        Text(
                            "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth(),
                        )
                    }
                }
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 20.dp)

                ) {
                    RadioButton(
                        selected = isAssistedMode, onClick = { isAssistedMode = false  }, colors = RadioButtonColors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = Color.Black,
                            disabledUnselectedColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text("Individual", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "info",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(9.dp)
                            .align(Alignment.Top)
                    )
                    Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
                        Text(
                            "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth(),

                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ModePickerSetupPreview(){
    ModePickerSetup() }
