package com.example.pruebastfg.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DebugSettingScreen(
    changeSetupDoneStatus: () -> Unit, // Callback para cambiar el estado del setup
    setupStatus: Boolean // Estado actual del setup
){
        val colorScheme = MaterialTheme.colorScheme
        val colors = listOf(
            "primary" to colorScheme.primary,
            "onPrimary" to colorScheme.onPrimary,
            "primaryContainer" to colorScheme.primaryContainer,
            "onPrimaryContainer" to colorScheme.onPrimaryContainer,
            "inversePrimary" to colorScheme.inversePrimary,
            "secondary" to colorScheme.secondary,
            "onSecondary" to colorScheme.onSecondary,
            "secondaryContainer" to colorScheme.secondaryContainer,
            "onSecondaryContainer" to colorScheme.onSecondaryContainer,
            "tertiary" to colorScheme.tertiary,
            "onTertiary" to colorScheme.onTertiary,
            "tertiaryContainer" to colorScheme.tertiaryContainer,
            "onTertiaryContainer" to colorScheme.onTertiaryContainer,
            "background" to colorScheme.background,
            "onBackground" to colorScheme.onBackground,
            "surface" to colorScheme.surface,
            "onSurface" to colorScheme.onSurface,
            "surfaceVariant" to colorScheme.surfaceVariant,
            "onSurfaceVariant" to colorScheme.onSurfaceVariant,
            "surfaceTint" to colorScheme.surfaceTint,
            "inverseSurface" to colorScheme.inverseSurface,
            "inverseOnSurface" to colorScheme.inverseOnSurface,
            "error" to colorScheme.error,
            "onError" to colorScheme.onError,
            "errorContainer" to colorScheme.errorContainer,
            "onErrorContainer" to colorScheme.onErrorContainer,
            "outline" to colorScheme.outline,
            "outlineVariant" to colorScheme.outlineVariant,
            "scrim" to colorScheme.scrim,
        )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()

    ) {
        Text(text = "Cambiar setup state", style = MaterialTheme.typography.headlineSmall)
// Mostrar el estado actual del setup
        Text(
            text = "Estado del Setup: ${if (setupStatus) "Completado" else "No completado"}",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )/* Botón para navegar a la pantalla de todas las apps */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Botón para cambiar el estado del setup
            Button(
                onClick = changeSetupDoneStatus, modifier = Modifier.weight(1f)
            ) {
                Text(text = "Setup")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Lista de colores del tema actual", style = MaterialTheme.typography.headlineSmall)
        colors.forEach { (name, color) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color)
                    .padding(8.dp)
            ) {
                Text(text = name, color = if (color.luminance() > 0.5) Color.Black else Color.White)
            }
        }
    }
}