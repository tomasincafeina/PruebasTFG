package com.example.pruebastfg.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.items.AppItem
import com.example.pruebastfg.ui.models.AppModel
import com.example.pruebastfg.ui.sharedItems.BigLowButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllAppsListScreen(
    apps: List<AppModel>,
    onClickTerminar: (List<AppModel>) -> Unit,
    isSetup: Boolean
) {
    var searchText by remember { mutableStateOf("") }

    // Esta es la copia editable y 100% reactiva
    val selectableApps = remember {
        mutableStateListOf<AppModel>().apply {
            addAll(apps.map { it.copy() })
        }
    }

    // ⚠️ Asegúrate de actualizar si la lista original cambia (ej. al volver atrás)
    LaunchedEffect(apps) {
        selectableApps.clear()
        selectableApps.addAll(apps.map { it.copy() })
    }

    val filteredApps = remember(searchText, selectableApps) {
        if (searchText.isBlank()) selectableApps
        else selectableApps.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Barra de búsqueda
        SearchBar(
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = { Text(stringResource(R.string.buscar_aplicacion), color = MaterialTheme.colorScheme.onBackground) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search" , tint = MaterialTheme.colorScheme.onBackground)},
            modifier = Modifier
                .padding(end = 10.dp),
            shape = MaterialTheme.shapes.extraLarge,
            windowInsets = WindowInsets(0.dp),
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {}

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(filteredApps, key = { it.packageName }) { app ->
                AppItem(app = app, onClick = {
                    val index = selectableApps.indexOfFirst { it.packageName == app.packageName }
                    if (index != -1) {
                        val updated = app.copy(isSelected = !app.isSelected)
                        selectableApps[index] = updated // 💥 ¡Esto sí lo detecta Compose!
                    }
                })
            }
        }

        if (isSetup) {
            val selectedApps = selectableApps.filter { it.isSelected }

            BigLowButton({
                onClickTerminar(selectedApps)
                // Limpieza opcional
                selectableApps.replaceAll { it.copy(isSelected = false) }
            }, stringResource(R.string.terminar), false
            )

        }
    }
}



