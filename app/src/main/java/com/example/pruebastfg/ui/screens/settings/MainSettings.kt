package com.example.pruebastfg.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.ui.data.models.SettingModel

@Composable
fun MainSettoingsScreen(
    goToAddAppClick: () -> Unit,
    goToRemoveAppClick: () -> Unit,
    goToFavoriteAppsClick: () -> Unit,
) {
    val settingsItems = listOf(
        SettingModel("Añadir", Icons.Default.Add, goToAddAppClick),
        SettingModel("Eliminar", Icons.Default.Clear, goToRemoveAppClick),
        SettingModel("Favoritos", Icons.Default.Star, goToFavoriteAppsClick),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Top
    ) {
        items(settingsItems) { setting ->
            SettingItem(setting)
        }
    }
}

@Composable
fun SettingItem(setting: SettingModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Make the card square
            .padding(12.dp)
            .clickable { setting.onClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = setting.icon, // Icono de configuración
                contentDescription = "Icono de configuración",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = setting.title, fontSize = 20.sp, maxLines = 2, textAlign = TextAlign.Center,
            )
        }
    }
}