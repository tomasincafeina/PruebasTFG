package com.example.pruebastfg.ui.screens.setup

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.AppScreens
import com.example.pruebastfg.ui.SetupSubScreens
import com.example.pruebastfg.ui.sharedItems.PwdCorrectIcon
import com.example.pruebastfg.ui.sharedItems.PwdIncorrectIcon

// Función para verificar si nuestra app es el launcher predeterminado
private fun isAppDefaultLauncher(context: Context, packageName: String): Boolean {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    val resolveInfo =
        context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return resolveInfo?.activityInfo?.packageName == packageName
}

@Composable
fun ChangeLauncherSetup(
    navController: NavHostController
) {
    val context = LocalContext.current
    val packageName = context.packageName

    // Estado para almacenar si nuestra app es el launcher predeterminado
    val isDefaultLauncher by remember {
        mutableStateOf(isAppDefaultLauncher(context, packageName))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Text(
            stringResource(R.string.eligeme_como_tu_app_principal),
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        // Mostrar mensaje si ya somos el launcher predeterminado
        if (isDefaultLauncher) {
            Row {
                PwdCorrectIcon()
                Text(
                    "¡Ya estás usando EasyUI como launcher!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                )
            }
            navController.navigate(SetupSubScreens.mode.name)
        }
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
                    stringResource(R.string.cambia_tu_app_principal),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "info",
                modifier = Modifier
                    .size(30.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(R.string.explicacion_launcher),
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(3.dp)

            )
        }


    }
}



