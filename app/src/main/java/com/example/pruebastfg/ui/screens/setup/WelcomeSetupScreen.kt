package com.example.pruebastfg.ui.screens.setup

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.SetupSubScreens
import com.example.pruebastfg.ui.sharedItems.BigLowButton
import com.example.pruebastfg.ui.sharedItems.HybridStepper
// Función para verificar si nuestra app es el launcher predeterminado
private fun isAppDefaultLauncher(context: Context, packageName: String): Boolean {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    val resolveInfo =
        context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return resolveInfo?.activityInfo?.packageName == packageName
}

@Composable
fun WelcomeSetupScreen(
    navController: NavHostController,
    navigateForward: () -> Unit,
    modifier: Modifier = Modifier,
    setUpStatus: () -> Unit
) {
    val context = LocalContext.current
    val packageName = context.packageName
    val isDefaultLauncher by remember {
        mutableStateOf(isAppDefaultLauncher(context, packageName))
    }
    if (isDefaultLauncher) {
        navController.navigate(SetupSubScreens.mode.name)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)

    ) {
        Text(
            stringResource(R.string.bienvenido_a_easyway_ui),
            fontSize = 45.sp,
            // modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            lineHeight = 60.sp,
            modifier = Modifier.weight(1f)
        )
        //Spacer(modifier = Modifier.height(16.dp))
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = "logo",
            modifier = Modifier
                .size(120.dp)
                .weight(1f)
        )
        Text(
            stringResource(R.string.vamos_a_comenzar_con_el_proceso_de_ajuste),
            fontSize = 25.sp,
            modifier = Modifier
                .padding(20.dp)
                .weight(1f),
            textAlign = TextAlign.Center,
        )
    }
    BigLowButton({ navigateForward() }, stringResource(R.string.comenzar), false)

}