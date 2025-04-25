package com.example.pruebastfg.ui.screens.setup

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.RadioButton
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import android.provider.Settings
import androidx.compose.material3.ButtonDefaults

@Composable
fun ChangeLauncherSetup(
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            // ... (tu contenido existente) ...

            // Botón para configurar el launcher
            Button(
                onClick = {
                    try {
                        // Intent específico para ajustes de launcher (puede no funcionar en todos los dispositivos)
                        val intent = Intent(Settings.ACTION_HOME_SETTINGS)
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // Fallback: intent genérico para seleccionar launcher
                        val selectorIntent = Intent(Intent.ACTION_MAIN).apply {
                            addCategory(Intent.CATEGORY_HOME)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(selectorIntent)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text("Configurar Launcher", fontSize = 18.sp)
            }

            // ... (resto de tu contenido) ...
        }
        Text(
            "Eligeme como tu App principal",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Black, CircleShape)
            ) {
                Text("Cambia tu Launcher", fontSize = 15.sp, modifier = Modifier.padding(20.dp))
            }
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Black, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "settings",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable { }
                        .size(48.dp)
                        .padding(9.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black, shape = ShapeDefaults.Medium)
                .width(350.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "home"
                    )

                    Text("Default Launcher")
                    RadioButton(
                        selected = false, onClick = { /*TODO*/ }, colors = RadioButtonColors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = Color.Black,
                            disabledUnselectedColor = Color.Black
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "home"
                    )
                    Text("Default Launcher")
                    RadioButton(
                        selected = true, onClick = { /*TODO*/ }, colors = RadioButtonColors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Black,
                            disabledSelectedColor = Color.Black,
                            disabledUnselectedColor = Color.Black
                        )
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "info",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(48.dp)
                    .padding(5.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = ShapeDefaults.Medium
                    )
            ) {

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
