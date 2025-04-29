package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.ui.sharedItems.BigLowButton

@Composable
fun FinishedSetup(
    navController: NavHostController,
    toogleSetupStatus: () -> Unit,
    onClickFinished: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    )
    {
        Text(
            "¡Ya hemos completado el proceso de ajuste!",
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            lineHeight = 60.sp,
            modifier = Modifier.weight(1f)

        )
        Icon(Icons.Rounded.Favorite, contentDescription = "logo", modifier = Modifier.size(120.dp).weight(1f))

        Text(
            "Disfruta de Easy UI",
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp).weight(1f),
            textAlign = TextAlign.Center,
        )

    }
    BigLowButton({
        toogleSetupStatus()
        onClickFinished()
    }, "Terminar", false)
}

