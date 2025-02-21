package com.example.pruebastfg.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.ui.models.AppModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.data.storage.AppViewModelFactory
import com.example.pruebastfg.ui.data.storage.PreferencesRepository
import com.example.pruebastfg.ui.theme.Purple40
import kotlinx.coroutines.launch

enum class AppScreens(@StringRes val title: Int) {
    Setup(title = R.string.setup),
    Home(title = R.string.home),
    Configuration(title = R.string.configuration)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,

    userName: String
) {
    // Usamos CenterAlignedTopAppBar para centrar el título correctamente
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Hola, $userName!",
                style = MaterialTheme.typography.headlineLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MainScreen(
    onAppClick: (String) -> Unit,
    navController: NavHostController = rememberNavController()

) {
    //navhost
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = AppScreens.entries.find {
        it.name == backStackEntry?.destination?.route
    } ?: AppScreens.Home


    val context = LocalContext.current
    val repository = remember { PreferencesRepository(context) }
    val viewModel: AppViewModel = viewModel(factory = AppViewModelFactory(repository))

    val uiState by viewModel.uiState.collectAsState()
    val userName by repository.getUserName().collectAsState(initial = "Cargando...")

    val apps = viewModel.getInstalledApps(LocalContext.current)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() },
                modifier = Modifier,
                "$userName"
            )
        },
        bottomBar = {
            BottomAppBar(
                currentScreen = currentScreen,
                navController = navController,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = AppScreens.Setup.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            composable(route = AppScreens.Setup.name) {
                NameInput(
                    userName = uiState.userName,
                    onNameChange = { viewModel.updateUserName(it) },
                    btnSaveOnClick = {
                        viewModel.viewModelScope.launch {
                            repository.saveUserName(
                                uiState.userName,
                                context
                            ) // Guarda el nombre correctamente
                        }
//                    viewModelScope.launch:
//                    viewModelScope is a CoroutineScope tied to the ViewModel.
//                    It automatically cancels any running coroutines when the ViewModel is cleared
//                    (e.g., when the Activity/Fragment is destroyed).
//                    launch is used to start a new coroutine for non-blocking tasks.
                    },
                    btnClearOnClick = {
                        viewModel.viewModelScope.launch {
                            repository.clearUserName() // Borra el nombre correctamente
                        }
                    },
                    btnNextOnClick = {
                        navController.navigate(AppScreens.Home.name)
                    }
                )
            }
            composable(route = AppScreens.Home.name) {
                AppsListScreen(apps = apps, onAppClick = onAppClick)

            }
        }
    }
}

@Composable
fun BottomAppBar(
    currentScreen: AppScreens,
    navController: NavHostController,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        actions = {
            if (canNavigateBack) {
                Card(
                    modifier = Modifier
                        .clickable {navigateUp()}
                        .padding(10.dp)
                        .fillMaxSize()
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize(),

                        ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                        Text(
                            text = "Atrás",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)

                        )
                    }

                }
            }
        }
    )
}

@Composable
fun NameInput(
    userName: String,
    onNameChange: (String) -> Unit,
    btnSaveOnClick: () -> Unit = {},
    btnClearOnClick: () -> Unit = {},
    btnNextOnClick: () -> Unit = {}

) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { onNameChange(it) },
            label = { Text("Escribe tu nombre") }
        )

        Row {
            Button(
                onClick = btnSaveOnClick
            ) {
                Text("Guardar nombre")
            }
            Button(
                onClick = btnClearOnClick
            ) {
                Text("Borrar nombre")
            }
        }
        Button(
            onClick = btnNextOnClick
        ) {
            Text("Siguiente")

        }
    }
}


@Composable
fun AppsListScreen(apps: List<AppModel>, onAppClick: (String) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Top
    ) {
        items(apps) { app ->
            AppItem(app = app, onClick = { onAppClick(app.packageName) })
        }
    }
}

@Composable
fun AppItem(app: AppModel, onClick: () -> Unit) {
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