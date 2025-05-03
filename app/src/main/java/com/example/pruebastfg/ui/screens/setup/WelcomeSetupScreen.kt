package com.example.pruebastfg.ui.screens.setup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.sharedItems.BigLowButton
import com.example.pruebastfg.ui.sharedItems.HybridStepper

@Composable
fun WelcomeSetupScreen(
    navController: NavHostController,
    navigateForward: () -> Unit,
    modifier: Modifier = Modifier,
    setUpStatus: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)

    ) {
        Text(
            "Bienvenido \n a \n EasyWay UI",
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
            "Vamos a comenzar con el proceso de ajuste",
            fontSize = 25.sp,
            modifier = Modifier
                .padding(20.dp)
                .weight(1f),
            textAlign = TextAlign.Center,
        )
    }
    BigLowButton({ navigateForward() }, "Comenzar", false)

}