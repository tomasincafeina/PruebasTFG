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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation

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
        Text(
            "Elígeme como tu App principal",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Button(
                elevation = ButtonDefaults.buttonElevation(8.dp),
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
                colors = ButtonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier
                //.border(2.dp, Color.Black, CircleShape),
            ) {


                Text(
                    "Cambia tu App principal",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
//            Box(
//                modifier = Modifier
//                    .border(2.dp, Color.Black, CircleShape)
//                    .align(Alignment.CenterVertically)
//                    .clickable {
//                        try {
//                            // Intent específico para ajustes de launcher (puede no funcionar en todos los dispositivos)
//                            val intent = Intent(Settings.ACTION_HOME_SETTINGS)
//                            context.startActivity(intent)
//                        } catch (e: Exception) {
//                            // Fallback: intent genérico para seleccionar launcher
//                            val selectorIntent = Intent(Intent.ACTION_MAIN).apply {
//                                addCategory(Intent.CATEGORY_HOME)
//                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                            }
//                            context.startActivity(selectorIntent)
//                        }
//                    }
//            ) {
//                Icon(
//                    imageVector = Icons.Rounded.Settings,
//                    contentDescription = "settings",
//                    tint = Color.Black,
//                    modifier = Modifier
//                        .size(48.dp)
//                        .padding(9.dp)
//                )
//            }
        }
        //DOCUMENTACION esto no hace su funcion de explicar al usuario como cambiar el launcher, solo lo hace mas confuso
//        Box(
//            modifier = Modifier
//                .border(2.dp, Color.Black, shape = ShapeDefaults.Medium)
//                .width(350.dp)
//                .height(150.dp)
//                .align(Alignment.CenterHorizontally)
//                .clickable {
//                    try {
//                        // Intent específico para ajustes de launcher (puede no funcionar en todos los dispositivos)
//                        val intent = Intent(Settings.ACTION_HOME_SETTINGS)
//                        context.startActivity(intent)
//                    } catch (e: Exception) {
//                        // Fallback: intent genérico para seleccionar launcher
//                        val selectorIntent = Intent(Intent.ACTION_MAIN).apply {
//                            addCategory(Intent.CATEGORY_HOME)
//                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        }
//                        context.startActivity(selectorIntent)
//                    }
//                }
//        ) {
//            Column(
//                verticalArrangement = Arrangement.SpaceEvenly,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Home,
//                        contentDescription = "home"
//                    )
//
//                    Text("Default Launcher")
//                    RadioButton(
//                        selected = false, onClick = { /*TODO*/ }, colors = RadioButtonColors(
//                            selectedColor = Color.Black,
//                            unselectedColor = Color.Black,
//                            disabledSelectedColor = Color.Black,
//                            disabledUnselectedColor = Color.Black
//                        )
//                    )
//                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Face,
//                        contentDescription = "home"
//                    )
//                    Text("Default Launcher")
//                    RadioButton(
//                        selected = true, onClick = { /*TODO*/ }, colors = RadioButtonColors(
//                            selectedColor = Color.Black,
//                            unselectedColor = Color.Black,
//                            disabledSelectedColor = Color.Black,
//                            disabledUnselectedColor = Color.Black
//                        )
//                    )
//                }
//            }
//        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "info",
                modifier = Modifier
                    .size(30.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Un launcher es una aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(3.dp)

            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(
//                        MaterialTheme.colorScheme.surfaceVariant,
//                        shape = ShapeDefaults.Medium
//                    )
//            ) {
//
//                Text(
//                    "Un launcher un la aplicación que se abre al iniciar el dispositivo. Cuando selecciones EasyUI será la aplicación que se abre al iniciar el dispositivo. ",
//                    fontSize = 15.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .padding(13.dp)
//                        .fillMaxWidth(),
//                )
//            }
        }

    }
}
