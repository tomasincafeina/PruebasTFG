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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onAppClickAdd: (AppModel) -> Unit,
    onClickTerminar: () -> Unit,
    isSetup: Boolean
) {
    // Estado para el texto de búsqueda
    var searchText by remember { mutableStateOf("") }

    // Filtrar apps basado en el texto de búsqueda
    val filteredApps = remember(apps, searchText) {
        if (searchText.isBlank()) {
            apps
        } else {
            apps.filter { app ->
                app.name.contains(searchText, ignoreCase = true)
            }
        }
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
            placeholder = { Text("Buscar aplicación...") },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            windowInsets = WindowInsets(0, 0, 0, 0) // ✅ desactiva insets
        ) {}
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
            items(filteredApps) { app ->
                AppItem(app = app, onClick = { onAppClickAdd(app) })
            }
        }

        if (isSetup) {
            BigLowButton(onClickTerminar, stringResource(R.string.terminar), false)
        }
    }

}

