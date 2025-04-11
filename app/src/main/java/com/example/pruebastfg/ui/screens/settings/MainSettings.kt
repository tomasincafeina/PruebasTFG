package com.example.pruebastfg.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.ui.items.SettingItem
import com.example.pruebastfg.data.models.SettingModel
import com.example.pruebastfg.ui.sharedItems.BigLowButton

@Composable
fun MainSettoingsScreen(
    goToAddAppClick: () -> Unit,
    goToRemoveAppClick: () -> Unit,
    goToFavoriteAppsClick: () -> Unit,
    goToColorSettingClick: () -> Unit,
    goToDebugClick: () -> Unit,
    goToPasswordClick: () -> Unit,
    onClickAtras:() -> Unit,
) {
    val settingsItems = remember {
        listOf(
            SettingModel("Añadir", Icons.Rounded.AddCircle, goToAddAppClick),
            SettingModel("Eliminar", Icons.Rounded.Clear, goToRemoveAppClick),
            SettingModel("Favoritos", Icons.Rounded.Star, goToFavoriteAppsClick),
            SettingModel("Color", Icons.Rounded.Edit, goToColorSettingClick),
            SettingModel("Debug", Icons.Rounded.Build, goToDebugClick),
            SettingModel("Contraseña", Icons.Rounded.Lock, goToPasswordClick)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top
        ) {
            items(settingsItems) { setting ->
                SettingItem(setting)
            }
        }
        BigLowButton(onClickAtras, "Atrás", true)
    }
}
