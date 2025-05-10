package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.AppScreens
import com.example.pruebastfg.ui.SetupSubScreens
import com.example.pruebastfg.ui.items.AppItem
import com.example.pruebastfg.ui.models.AppModel
import com.example.pruebastfg.ui.sharedItems.BigLowButton
import com.example.pruebastfg.ui.sharedItems.BottomBarNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialAppAdd(
    apps: List<AppModel>,
    onClickTerminar: (List<AppModel>) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
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
            placeholder = {
                Text(
                    stringResource(R.string.buscar_aplicacion),
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
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
        BottomBarNavigation(
            isOK = selectableApps.any { it.isSelected },
            onBack = { onBack() },
            onNext = {
                val selected = selectableApps.filter { it.isSelected }
                onClickTerminar(selected)
                selectableApps.replaceAll { it.copy(isSelected = false) }
                onNext()
            }
        )
    }
}

