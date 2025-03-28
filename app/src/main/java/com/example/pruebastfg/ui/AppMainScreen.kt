package com.example.pruebastfg.ui

import android.graphics.Bitmap
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.ui.models.AppModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.R
import com.example.pruebastfg.data.items.AppItem
import com.example.pruebastfg.data.items.AppItemProto
import com.example.pruebastfg.data.storage.AppsProtoRepository
import com.example.pruebastfg.data.storage.appsDataStore
import com.example.pruebastfg.data.storage.ViewModelFactory
import com.example.pruebastfg.data.storage.PreferencesRepository
import com.example.pruebastfg.ui.screens.settings.AllAppsListScreen
import com.example.pruebastfg.ui.screens.settings.ThemeSettingScreen
import com.example.pruebastfg.ui.screens.settings.SelectFavoriteApps
import com.example.pruebastfg.ui.screens.settings.MainSettoingsScreen
import com.example.pruebastfg.ui.screens.settings.RemoveApps
import com.example.pruebastfg.ui.screens.setup.UserNameSetupScreen
import com.example.pruebastfg.ui.screens.setup.WelcomeSetupScreen


enum class AppScreens(@StringRes val title: Int) {
    Setup(title = R.string.setup),
    Home(title = R.string.home),
    AddApp(title = R.string.addapps),
    RemoveApp(title = R.string.removeapps),
    FavoriteApps(title = R.string.favoriteapps),
    Settings(title = R.string.settings),
    ColorSetting(title = R.string.colorsetting)
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
    onSettingsClick: () -> Unit,  // Callback para manejar el clic en settings
    modifier: Modifier = Modifier,

    topBarTitle: String
) {
    // Usamos CenterAlignedTopAppBar para centrar el título correctamente
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topBarTitle,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        actions = {
            if (currentScreen.name == AppScreens.Home.name) {
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Rounded.Settings, // Icono de configuración
                        contentDescription = "Settings",
                        modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },

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
    onAppClick: (String) -> Unit, navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel,
    context: android.content.Context
) {
    // Obtener la entrada actual de la pila de navegación
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Determinar la pantalla actual
    val currentScreen = AppScreens.entries.find {
        it.name == backStackEntry?.destination?.route
    } ?: AppScreens.Home

    // Determinar si la pantalla actual es parte del flujo de Setup
    val isSetupFlow = remember(backStackEntry) {
        backStackEntry?.destination?.route in SetupSubScreens.entries.map { it.name }
    }
//    val context = LocalContext.current
//    val prefsRepo = remember { PreferencesRepository(context) }
//    val appsRepo = remember { AppsProtoRepository(context,context.appsDataStore) }

    //val viewModel: AppViewModel = viewModel(factory = ViewModelFactory(prefsRepo, appsRepo, context))
    // Observar datos
    val userName by viewModel.userName.collectAsState(initial = "")
    val isThemeDark = viewModel.isThemeDark.collectAsState(initial = true)
    val setupStatus by viewModel.setupDone.collectAsState(initial = false)

    val startDestination by viewModel.startDestination.collectAsState()
    val appsProto by viewModel.apps.collectAsState(initial = emptyList())


    val uiState by viewModel.uiState.collectAsState()

    Scaffold(containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
        AppTopBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.popBackStack() },
            onSettingsClick = { navController.navigate(AppScreens.Settings.name) },
            modifier = Modifier,
            topBarTitle =
            if (currentScreen == AppScreens.Home && userName!!.isNotBlank()) {
                "Hola, $userName!"
            } else if(currentScreen == AppScreens.Home){
                ""
            }else{
                currentScreen.name
            },

        )
    }, bottomBar = {
        // Mostrar el BottomAppBar solo si estamos en el flujo de Setup
        if (isSetupFlow) {
            SetupBottomAppBar(currentScreen = SetupSubScreens.entries.find {
                it.name == backStackEntry?.destination?.route
            } ?: SetupSubScreens.welcome, navController = navController, onNext = {
                when (backStackEntry?.destination?.route) {
                    SetupSubScreens.welcome.name -> navController.navigate(SetupSubScreens.username.name)
                    SetupSubScreens.username.name -> navController.navigate(AppScreens.Home.name)
                }
            }, onBack = { navController.popBackStack() })
        }
    }) { innerPadding ->
        NavHost(
            //cambiar nmbre setupStatus
            navController = navController, startDestination = if (setupStatus == false) {
                AppScreens.Setup.name
            } else {
                AppScreens.Home.name
            }, modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Pantalla de Setup
            composable(route = AppScreens.Setup.name,) {
                WelcomeSetupScreen(navController = navController,
                    navigateForward = { navController.navigate(SetupSubScreens.username.name) },
                    modifier = Modifier,
                    setUpStatus = { viewModel.toggleSetupDone() })
            }

            // Subpantallas de Setup
            composable(route = SetupSubScreens.username.name) {
                UserNameSetupScreen(
                    navController = navController,
                    userName = uiState.userName,
                    onNameChange = { viewModel.updateUserName(it) },
                    btnSaveOnClick = { viewModel.saveUserName(uiState.userName, context) },
                    btnClearOnClick = {
                        viewModel.updateUserName("")
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
                setupStatus?.let { it ->
//                    AppsListScreen(
//                        apps = apps, onAppClick = onAppClick,
//                        changeSetupDoneStatus = {
//                            viewModel.toggleSetupDone()
//                        },
//                        {
//                            navController.navigate(AppScreens.AllApps.name)
//                        },
//                        setupStatus = it
//
//                    )

                    AppsFromProto(appsProto, onAppClick = onAppClick, changeSetupDoneStatus = {
                        viewModel.toggleSetupDone()
                    }, {
                        navController.navigate(AppScreens.AddApp.name)
                    }, {
                        navController.navigate(AppScreens.RemoveApp.name)
                    }, {
                        navController.navigate(AppScreens.FavoriteApps.name)
                    }, setupStatus = it
                    )
                }
            }
            composable(route = AppScreens.Settings.name) {
                MainSettoingsScreen(
                    { navController.navigate(AppScreens.AddApp.name) },
                    { navController.navigate(AppScreens.RemoveApp.name) },
                    { navController.navigate(AppScreens.FavoriteApps.name) },
                    { navController.navigate(AppScreens.ColorSetting.name) },
                    { navController.popBackStack() }
                    )
            }
            composable(route = AppScreens.ColorSetting.name) {
                ThemeSettingScreen(isThemeDark = isThemeDark, { viewModel.toggleTheme() })
            }
            composable(route = AppScreens.AddApp.name) {
                val allApps by viewModel.allApps.collectAsState()

                AllAppsListScreen(apps = allApps, onAppClickAdd = { app ->
                    val appExists = appsProto.any { it.first.packageName == app.packageName }

                    if (!appExists) {

                        viewModel.toogleIsSelected(app)
                        viewModel.addAppXX(
                            name = app.name, packageName = app.packageName, icon = app.icon
                        )
                        Toast.makeText(context, "Añadiendo ${app.name}...", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.toogleIsSelected(app)
                        Toast.makeText(
                            context, "${app.name} ya está en la lista", Toast.LENGTH_SHORT
                        ).show()
                    }
                }, onClickTerminar = { navController.navigate(AppScreens.Home.name) })
            }
            composable(route = AppScreens.RemoveApp.name) {

                RemoveApps(appsProto, onAppClick = { app ->
                    viewModel.removeApp(app.id)
                }, onClickTerminar = { navController.navigate(AppScreens.Home.name) })
            }
            composable(route = AppScreens.FavoriteApps.name) {

                SelectFavoriteApps(appsProto, onAppClick = { app ->
                    viewModel.toggleFavorite(app.id)
                }, onClickTerminar = { navController.navigate(AppScreens.Home.name) }
                )
            }
        }
    }
}

@Composable
fun AppsListScreen(
    apps: List<AppModel>,
    onAppClick: (String) -> Unit,
    changeSetupDoneStatus: () -> Unit,
    goToAllApps: () -> Unit,
    setupStatus: Boolean
) {
    Column {
        Button(onClick = (changeSetupDoneStatus)) {
            Text(text = "Cambiar estado del Setup")
        }
        Button(onClick = goToAllApps) {
            Text(text = "Ver todas las apps")
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
fun AppsFromProto(
    apps: List<Pair<AppInfo, Bitmap?>>, // Lista de aplicaciones con sus iconos
    onAppClick: (String) -> Unit, // Callback cuando se hace clic en una app
    changeSetupDoneStatus: () -> Unit, // Callback para cambiar el estado del setup
    goToAddApp: () -> Unit, // Callback para navegar a la pantalla de todas las apps
    goToRemoveApps: () -> Unit, // Callback para navegar a la pantalla de borrar apps
    goToFavoriteApps: () -> Unit, // Callback para navegar a la pantalla de borrar apps

    setupStatus: Boolean // Estado actual del setup
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Mostrar el estado actual del setup
        Text(
            text = "Estado del Setup: ${if (setupStatus) "Completado" else "No completado"}",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )/* Botón para navegar a la pantalla de todas las apps */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Botón para cambiar el estado del setup
            Button(
                onClick = changeSetupDoneStatus, modifier = Modifier.weight(1f)
            ) {
                Text(text = "Setup")
            }
        }
        // Lista de aplicaciones en un grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top
        ) {
            items(apps) { (appInfo, bitmap) ->
                AppItemProto(appInfo = appInfo,
                    bitmap = bitmap,
                    onClick = { onAppClick(appInfo.packageName) })
            }
        }
    }

}


