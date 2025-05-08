package com.example.pruebastfg.ui.screens.settings

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.items.AppItemProto
import com.example.pruebastfg.ui.sharedItems.BigLowButton


@Composable
fun SelectFavoriteApps(
    apps: List<Pair<AppInfo, Bitmap?>>, // Lista de aplicaciones con sus iconos
    onAppClick: (AppInfo) -> Unit, // Callback cuando se hace clic en una app
    onClickTerminar: () -> Unit,
    fontSize: TextUnit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        // Lista de aplicaciones en un grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top
        ) {
            items(apps) { (appInfo, bitmap) ->
                AppItemProto(
                    appInfo = appInfo,
                    bitmap = bitmap,
                    onClick = { onAppClick(appInfo) },
                    fontSize = fontSize)
            }
        }
        BigLowButton(onClickTerminar, stringResource(R.string.terminar), false)
    }
}