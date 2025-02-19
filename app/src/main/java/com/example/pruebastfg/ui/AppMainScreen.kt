package com.example.pruebastfg.ui

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.models.AppModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.example.pruebastfg.ui.data.storage.AppViewModelFactory
import com.example.pruebastfg.ui.data.storage.PreferencesRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlin.coroutines.coroutineContext

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
fun MainScreen(
    onAppClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { PreferencesRepository(context) }
    val viewModel: AppViewModel = viewModel(factory = AppViewModelFactory(repository))

    val uiState by viewModel.uiState.collectAsState()
    val userName by repository.getUserName().collectAsState(initial = "Cargando...")

    val apps = viewModel.getInstalledApps(LocalContext.current)

    Scaffold(
        topBar = { AppTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Aquí puedes agregar otros composables, como un título, botones, etc.
            Text(
                text = "Usuario: $userName",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            // Input para escribir el nombre
            NameInput(
                userName = uiState.userName,
                onNameChange = { viewModel.updateUserName(it) },
                btnOnClick = {
                    viewModel.viewModelScope.launch {
                        repository.saveUserName(uiState.userName) // Guarda el nombre correctamente
                    }
//                    viewModelScope.launch:
//                    viewModelScope is a CoroutineScope tied to the ViewModel.
//                    It automatically cancels any running coroutines when the ViewModel is cleared
//                    (e.g., when the Activity/Fragment is destroyed).
//                    launch is used to start a new coroutine for non-blocking tasks.
                }
            )

            // Mostrar la lista de aplicaciones
            AppsListScreen(apps = apps, onAppClick = onAppClick)
        }

    }
}

@Composable
fun NameInput(userName: String, onNameChange: (String) -> Unit, btnOnClick: () -> Unit = {}) {

    Column {
        OutlinedTextField(
            value = userName,
            onValueChange = { onNameChange(it) },
            label = { Text("Escribe tu nombre") }
        )
        Button(
            onClick = btnOnClick
        ) {
            Text("Guardar nombre")
        }
    }
}


@Composable
fun AppsListScreen(apps: List<AppModel>, onAppClick: (String) -> Unit) {
//    LazyColumn {
//        items(apps) { app ->
//            AppListItem(app = app, onClick = { onAppClick(app.packageName) })
//        }
//    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Top
    ) {
        items(apps) { app ->
            AppListItem(app = app, onClick = { onAppClick(app.packageName) })
        }
    }
}

@Composable
fun AppListItem(app: AppModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Make the card square
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = app.icon.asImageBitmap(),
                contentDescription = app.name,
                modifier = Modifier
                    .size(90.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = app.name,
                fontSize = 20.sp,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
        }
    }
}