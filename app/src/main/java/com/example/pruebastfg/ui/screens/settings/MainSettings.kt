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
import androidx.compose.material.icons.rounded.AccessibilityNew
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LockReset
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.items.SettingItem
import com.example.pruebastfg.data.models.SettingModel
import com.example.pruebastfg.ui.sharedItems.BigLowButton

@Composable
fun MainSettingsScreen(
    goToAddAppClick: () -> Unit,
    goToRemoveAppClick: () -> Unit,
    goToFavoriteAppsClick: () -> Unit,
    goToColorSettingClick: () -> Unit,
    goToDebugClick: () -> Unit,
    goToPasswordClick: () -> Unit,
    onClickAtras:() -> Unit,
    goToChangeLauncherClick: () -> Unit,
    goToChangeModeClick: () -> Unit,
    fontSize: TextUnit
) {
    val settingsItems = remember {
        listOf(
            SettingModel(R.string.anadir, Icons.Rounded.AddCircle, goToAddAppClick),
            SettingModel(R.string.eliminar, Icons.Rounded.Clear, goToRemoveAppClick),
            SettingModel(R.string.favoritos, Icons.Rounded.Star, goToFavoriteAppsClick),
            SettingModel(R.string.color, Icons.Rounded.ColorLens, goToColorSettingClick),
            SettingModel(R.string.reset_contrasena, Icons.Rounded.LockReset, goToPasswordClick),
            SettingModel(R.string.launcher, Icons.Rounded.Home, goToChangeLauncherClick),
            SettingModel(R.string.mode, Icons.Rounded.AccessibilityNew, goToChangeModeClick),
            SettingModel(R.string.debug, Icons.Rounded.Build, goToDebugClick),
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
                SettingItem(setting, fontSize)
            }
        }
        BigLowButton(onClickAtras, stringResource(R.string.atras), true)
    }
}
