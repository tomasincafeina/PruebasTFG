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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.collectAsState
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
import com.example.pruebastfg.ui.screens.setup.UserNameSetupScreen
import com.example.pruebastfg.ui.screens.setup.WelcomeSetupScreen

import kotlinx.coroutines.launch


enum class AppScreens(@StringRes val title: Int) {
    Setup(title = R.string.setup),
    Home(title = R.string.home),
    Configuration(title = R.string.configuration)
}

enum class SetupSubScreens(@StringRes val title: Int) {
    welcome(title = R.string.welcome),
    username(title = R.string.username),
    modecolor(title = R.string.modecolor),
    fontsize(title = R.string.fontsize),
    launcher(title = R.string.launcher)
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
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        }
    )
}

@Composable
fun MainScreen(
    onAppClick: (String) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    // Obtener la entrada actual de la pila de navegación
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Determinar la pantalla actual
    val currentScreen = AppScreens.entries.find {
        it.name == backStackEntry?.destination?.route
    } ?: AppScreens.Home

    // Determinar si la pantalla actual es parte del flujo de Setup
    val isSetupFlow = backStackEntry?.destination?.route in SetupSubScreens.entries.map { it.name }

    val context = LocalContext.current
    val repository = remember { PreferencesRepository(context) }
    val viewModel: AppViewModel = viewModel(factory = AppViewModelFactory(repository))

    val uiState by viewModel.uiState.collectAsState()
    val userName by repository.getUserName().collectAsState(initial = "...")
    val setupStatus by repository.getSetupStatus().collectAsState(initial = null)
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
            // Mostrar el BottomAppBar solo si estamos en el flujo de Setup
            if (isSetupFlow) {
                SetupBottomAppBar(
                    currentScreen = SetupSubScreens.entries.find {
                        it.name == backStackEntry?.destination?.route
                    } ?: SetupSubScreens.welcome,
                    navController = navController,
                    onNext = {
                        when (backStackEntry?.destination?.route) {
                            SetupSubScreens.welcome.name -> navController.navigate(SetupSubScreens.username.name)
                            SetupSubScreens.username.name -> navController.navigate(AppScreens.Home.name)
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (setupStatus == false) {
                AppScreens.Setup.name
            } else {
                AppScreens.Home.name
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Pantalla de Setup
            composable(route = AppScreens.Setup.name) {
                WelcomeSetupScreen(
                    navController = navController,
                    navigateForward = { navController.navigate(SetupSubScreens.username.name) },
                    modifier = Modifier,
                    setUpStatus = {
                        viewModel.viewModelScope.launch {
                            repository.setSetupDone()
                        }
                    }
                )
            }

            // Subpantallas de Setup
            composable(route = SetupSubScreens.username.name) {
                UserNameSetupScreen(
                    navController = navController,
                    userName = uiState.userName,
                    onNameChange = { viewModel.updateUserName(it) },
                    btnSaveOnClick = {
                        viewModel.viewModelScope.launch {
                            repository.saveUserName(uiState.userName, context)
                        }
                    },
                    btnClearOnClick = {
                        viewModel.viewModelScope.launch {
                            repository.clearUserName()
                        }
                    },
                    btnNextOnClick = {
                        navController.navigate(AppScreens.Home.name) {
                            popUpTo(AppScreens.Setup.name) { inclusive = true }
                        }
                    },
                    setupStatus = setupStatus!!
                )
            }

            // Pantalla de Home
            composable(route = AppScreens.Home.name) {
                setupStatus?.let { it1 ->
                    AppsListScreen(
                        apps = apps, onAppClick = onAppClick,
                        changeSetupDoneStatus = {
                            viewModel.viewModelScope.launch {
                                repository.setSetupDoneContrario()
                            }
                        },
                        setupStatus = it1
                    )
                }
            }
        }
    }
}

@Composable
fun AppsListScreen(
    apps: List<AppModel>,
    onAppClick: (String) -> Unit,
    changeSetupDoneStatus: () -> Unit,
    setupStatus: Boolean
) {
    Column {
        Button(onClick = (changeSetupDoneStatus)) {
            Text(text = "Cambiar estado del Setup")
        }
        Text(text = setupStatus.toString())
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