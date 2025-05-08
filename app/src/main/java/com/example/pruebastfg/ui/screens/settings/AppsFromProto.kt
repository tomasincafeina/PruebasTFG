package com.example.pruebastfg.ui.screens.settings

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.AppInfo
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Shape
import com.example.pruebastfg.ui.items.AppItemProto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsFromProto(
    apps: List<Pair<AppInfo, Bitmap?>>, // Lista de aplicaciones con sus iconos
    onAppClick: (String) -> Unit, // Callback cuando se hace clic en una app
    //changeSetupDoneStatus: () -> Unit, // Callback para cambiar el estado del setup
    goToAddApp: () -> Unit, // Callback para navegar a la pantalla de todas las apps
    goToRemoveApps: () -> Unit, // Callback para navegar a la pantalla de borrar apps
    goToFavoriteApps: () -> Unit, // Callback para navegar a la pantalla de borrar apps
    fontSize: TextUnit,
    isAssitedMode: Boolean,
    goToSettings: () -> Unit, // Callback para navegar a la pantalla de ajustes
    //setupStatus: Boolean // Estado actual del setup
) {
    // Estado para el texto de búsqueda
    var searchText by remember { mutableStateOf("") }

    // Filtrar apps basado en el texto de búsqueda
    val filteredApps = remember(apps, searchText) {
        if (searchText.isBlank()) {
            apps
        } else {
            apps.filter { (appInfo, _) ->
                appInfo.name.contains(searchText, ignoreCase = true)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isAssitedMode == false) {
            // Barra de búsqueda
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SearchBar(
                    query = searchText,
                    onQueryChange = { searchText = it },
                    onSearch = {},
                    active = false,
                    onActiveChange = {},
                    placeholder = { Text("Buscar aplicación...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    windowInsets = WindowInsets(0.dp)
                ) {}

                Box(
                    modifier = Modifier
                        .size(56.dp)  // Standard FAB size
                ) {
                    IconButton(
                        onClick = { goToSettings() },
                        modifier = Modifier
                            .matchParentSize(),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
        if (apps.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(30.dp)
            ) {
                Text(
                    "No hay aplicaciones añadidas",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 60.sp
                )
                Spacer(modifier = Modifier.height(40.dp))
                Card(
                    modifier = Modifier
                        .size(100.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { goToAddApp() }
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Añadir app",
                            modifier = Modifier
                                .size(70.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
        //Esto estaba aqui para debuggear el estado del setup pero ahora esta ne debug
        // Mostrar el estado actual del setup
//        Text(
//            text = "Estado del Setup: ${if (setupStatus) "Completado" else "No completado"}",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(5.dp)
//        )/* Botón para navegar a la pantalla de todas las apps */
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            // Botón para cambiar el estado del setup
//            Button(
//                onClick = changeSetupDoneStatus, modifier = Modifier.weight(1f)
//            ) {
//                Text(text = "Setup")
//            }
//        }
        // Lista de aplicaciones en un grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top
        ) {
            items(filteredApps) { (appInfo, bitmap) ->

                //Botón para añadir app cuando no hay ninguna
                // LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                //    item(span = { GridItemSpan(2) }) {
                //        Text(text = "Añadir apps", textAlign = TextAlign.Center)
                //    }
                //    item(span = { GridItemSpan(2) }) {
                //        Button(onClick = { goToAddApp() }, modifier = Modifier.fillMaxWidth()) {
                //            Text("+")
                //        }
                //    }
                //}

                AppItemProto(
                    appInfo = appInfo,
                    bitmap = bitmap,
                    onClick = { onAppClick(appInfo.packageName) },
                    fontSize = fontSize
                )
            }
        }
    }
}
