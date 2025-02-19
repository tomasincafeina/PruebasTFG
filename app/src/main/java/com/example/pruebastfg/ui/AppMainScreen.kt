package com.example.pruebastfg.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.data.AppUiState
import com.example.pruebastfg.ui.models.AppModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
) {
    CenterAlignedTopAppBar(
        // Usamos CenterAlignedTopAppBar para centrar el título correctamente
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun MainScreen(onAppClick: (String) -> Unit) {
    val apps = AppViewModel().getInstalledApps(LocalContext.current)
    AppViewModel().logInstalledApps(LocalContext.current)
    Scaffold(
        topBar = { AppTopBar() }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            // Aquí puedes agregar otros composables, como un título, botones, etc.
            Text(
                text = "Mis Aplicaciones",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )

            // Mostrar la lista de aplicaciones
            AppListScreen(apps = apps, onAppClick = onAppClick)
        }

    }
}


@Composable
fun AppListScreen(apps: List<AppModel>, onAppClick: (String) -> Unit) {
    LazyColumn {
        items(apps) { app ->
            AppListItem(app = app, onClick = { onAppClick(app.packageName) })
        }
    }
}

@Composable
fun AppListItem(app: AppModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = app.icon.asImageBitmap(),
            contentDescription = app.name,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = app.name,
            fontSize = 18.sp
        )
    }
}