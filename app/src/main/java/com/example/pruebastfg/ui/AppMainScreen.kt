package com.example.pruebastfg.ui

import android.graphics.Bitmap
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.ui.models.AppModel
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.items.AppItem
import com.example.pruebastfg.ui.items.AppItemProto
import com.example.pruebastfg.ui.items.BottomIndivMode
import com.example.pruebastfg.ui.screens.settings.pwd.PasswordScreen
import com.example.pruebastfg.ui.screens.settings.AllAppsListScreen
import com.example.pruebastfg.ui.screens.settings.AppsFromProto
import com.example.pruebastfg.ui.screens.settings.DebugSettingScreen
import com.example.pruebastfg.ui.screens.settings.ThemeSettingScreen
import com.example.pruebastfg.ui.screens.settings.SelectFavoriteApps
import com.example.pruebastfg.ui.screens.settings.MainSettingsScreen
import com.example.pruebastfg.ui.screens.settings.RemoveApps
import com.example.pruebastfg.ui.screens.setup.ChangeLauncherSetup
import com.example.pruebastfg.ui.screens.setup.FinishedSetup
import com.example.pruebastfg.ui.screens.setup.FontSizeSetup
import com.example.pruebastfg.ui.screens.setup.ModePickerSetup
import com.example.pruebastfg.ui.screens.setup.PasswordSetupScreen
import com.example.pruebastfg.ui.screens.setup.ThemePickerSetup
import com.example.pruebastfg.ui.screens.setup.UserNameSetupScreen
import com.example.pruebastfg.ui.screens.setup.WelcomeSetupScreen


enum class AppScreens(@StringRes val title: Int) {
    Setup(title = R.string.setup),
    FinishedSetup(title = R.string.finished_setup),
    Home(title = R.string.home),
    AddApp(title = R.string.addapps),
    RemoveApp(title = R.string.removeapps),
    FavoriteApps(title = R.string.favoriteapps),
    ColorSetting(title = R.string.colorsetting),
    Settings(title = R.string.settings),


}

enum class SetupSubScreens(@StringRes val title: Int) {
    welcome(title = R.string.welcome),
    username(title = R.string.username),
    theme(title = R.string.theme),
    fontsize(title = R.string.fontsize),
    launcher(title = R.string.launcher),
    mode(title = R.string.mode),
    pwd(title = R.string.pwd),
    initialapps(title = R.string.initialapps),


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: Enum<*>,
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
//                IconButton(onClick = onSettingsClick) {
//                    Icon(
//                        imageVector = Icons.Rounded.Settings, // Icono de configuración
//                        contentDescription = "Settings",
//                        modifier = Modifier.size(30.dp),
//                        tint = MaterialTheme.colorScheme.onBackground
//                    )
//                }
                Box(
                    modifier = Modifier.padding(
                        10.dp
                    )
                        .size(56.dp)  // Standard FAB size
                ) {
                    IconButton(
                        onClick = { onSettingsClick() },
                        modifier = Modifier
                            .matchParentSize(),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onBackground
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarOnLoading(
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "",
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
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
    } ?: SetupSubScreens.entries.find {
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
    val userName by remember { viewModel.userName }.collectAsState(initial = "")
    val isThemeDark by remember { viewModel.isThemeDark }.collectAsState(initial = null)
    val setupStatus by remember { viewModel.setupDone }.collectAsState(initial = null)
    val colorTheme by remember { viewModel.colorTheme }.collectAsState(initial = "blue")
    val isAssistedMode by remember { viewModel.isAssistedMode }.collectAsState(initial = null)

    val startDestination by viewModel.startDestination.collectAsState()
    val appsProto by viewModel.apps.collectAsState(initial = emptyList())

    val uiState by viewModel.uiState.collectAsState()
    if (setupStatus != null && isThemeDark != null) { // Espera hasta que setupStatus y isThemeDark tenga un valor real
        Scaffold(containerColor = MaterialTheme.colorScheme.surface,
            topBar = {
                if ( currentScreen == AppScreens.Settings || isAssistedMode == true && currentScreen == AppScreens.Home || currentScreen == SetupSubScreens.username) {
                    AppTopBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.popBackStack() },
                        onSettingsClick = {
                            //Documentacion
                            //Si esta en modo asistido se muestra el password y si no lo esta no pasa por el password
                            if (isAssistedMode == true) {
                                navController.navigate("password")
                            } else {
                                navController.navigate(AppScreens.Settings.name)
                            }
                        },
                        modifier = Modifier,
                        topBarTitle =
                        if (currentScreen == AppScreens.Home && userName!!.isNotBlank() || currentScreen == SetupSubScreens.username) {
                            stringResource(R.string.hola, userName!!)
                        } else if (currentScreen == SetupSubScreens.username) {
                            stringResource(R.string.hola, userName!!)
                        } else {
                            stringResource(R.string.settings)
                        },

                        )
                }
            }, bottomBar = {
                // Mostrar el BottomAppBar solo si estamos en el flujo de Setup
                if (isSetupFlow) {
                    SetupBottomAppBar(currentScreen = SetupSubScreens.entries.find {
                        it.name == backStackEntry?.destination?.route
                    } ?: SetupSubScreens.welcome, navController = navController, onNext = {
                        when (backStackEntry?.destination?.route) {
                            SetupSubScreens.welcome.name -> navController.navigate(SetupSubScreens.launcher.name)
                            SetupSubScreens.launcher.name -> navController.navigate(SetupSubScreens.mode.name)
                            //si esta en modo asistido no te pide el nombre de usuario
                            SetupSubScreens.mode.name -> if (isAssistedMode == true) {
                                navController.navigate(SetupSubScreens.username.name)
                            } else {
                                navController.navigate(SetupSubScreens.theme.name)
                            }
                            SetupSubScreens.username.name -> navController.navigate(SetupSubScreens.theme.name)
                            SetupSubScreens.theme.name -> navController.navigate(SetupSubScreens.fontsize.name)
                            SetupSubScreens.fontsize.name -> navController.navigate(SetupSubScreens.initialapps.name)
                            //si esta en modo individual no te pide contraseña
                            SetupSubScreens.initialapps.name -> if (isAssistedMode == true) {
                                navController.navigate(SetupSubScreens.pwd.name)
                            } else {
                                navController.navigate(AppScreens.FinishedSetup.name)
                            }
                            SetupSubScreens.pwd.name -> navController.navigate(AppScreens.FinishedSetup.name)
                            AppScreens.FinishedSetup.name -> navController.navigate(AppScreens.Home.name)


                        }
                    }, onBack = { navController.popBackStack() })
                }
//                if (currentScreen == AppScreens.Home) {
//                    BottomIndivMode(
//                        {
//                            if (isAssistedMode == true) {
//                                 navController.navigate("password")
//                            } else {
//                                 navController.navigate(AppScreens.Settings.name)
//                            }
//                        }
//                    )
//                }
            }) { innerPadding ->
            NavHost(
                //cambiar nmbre setupStatus
                navController = navController,
                startDestination = if (setupStatus == true) {
                    AppScreens.Home.name
                } else {
                    AppScreens.Setup.name
                }, modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Pantalla de Setup
                composable(route = AppScreens.Setup.name) {
                    WelcomeSetupScreen(navController = navController,
                        navigateForward = { navController.navigate(SetupSubScreens.launcher.name) },
                        modifier = Modifier,
                        setUpStatus = { viewModel.toggleSetupDone() })
                }
                composable(route = SetupSubScreens.launcher.name) {
                    ChangeLauncherSetup(navController)
                }
                composable(route = SetupSubScreens.mode.name) {
                    ModePickerSetup(
                        navController,
                        { viewModel.setToIndividualMode() },
                        { viewModel.setToAssistedMode() })
                }
                // Subpantallas de Setup
                composable(route = SetupSubScreens.username.name) {
                    UserNameSetupScreen(
                        navController = navController,
                        userName = uiState.userName,
                        viewModel, context
                    )
                }
                composable(route = SetupSubScreens.theme.name) {
                    ThemePickerSetup(
                        navController = navController,
                        isThemeDark = isThemeDark!!,
                        { viewModel.changeThemeToDark() },
                        { viewModel.changeThemeToLight() },
                        colorTheme,
                        { viewModel.setThemeColor(it) },
                        { navController.navigate(AppScreens.Home.name) })
                }
                composable(route = SetupSubScreens.fontsize.name) {
                    FontSizeSetup(
                        navController = navController,
                        viewModel,
                    )
                }
                composable(route = SetupSubScreens.initialapps.name) {
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
                    },
                        onClickTerminar = { navController.navigate(AppScreens.Home.name) },
                        isSetup = setupStatus!!)
                }
                composable(route = SetupSubScreens.pwd.name) {
                    PasswordSetupScreen(
                        navController,
                        pwd = uiState.pwd,
                        onPasswordChange = { viewModel.updatePassword(it) },
                        setPassword = { viewModel.setPassword(uiState.pwd) }
                    )
                }

                composable(route = AppScreens.FinishedSetup.name) {
                    FinishedSetup(
                        navController,
                        { viewModel.toggleSetupDone() },
                        { navController.navigate(AppScreens.Home.name) }
                    )
                }
////////////////////////////////////////FINAL SETUP////////////////////////////////////////////
                // Pantalla de Home
                composable(route = AppScreens.Home.name) {
                    setupStatus?.let { it ->
                        AppsFromProto(
                            appsProto,
                            onAppClick = onAppClick,
                            goToAddApp = { navController.navigate(AppScreens.AddApp.name) },
                            goToRemoveApps = { navController.navigate(AppScreens.RemoveApp.name) },
                            goToFavoriteApps = { navController.navigate(AppScreens.FavoriteApps.name) },
                            viewModel.fontSize.collectAsState().value,
                            isAssitedMode = isAssistedMode!!,
                            {navController.navigate(AppScreens.Settings.name)}
                        )
                    }
                }
                composable(route = AppScreens.Settings.name) {
                    MainSettingsScreen(
                        { navController.navigate(AppScreens.AddApp.name) },
                        { navController.navigate(AppScreens.RemoveApp.name) },
                        { navController.navigate(AppScreens.FavoriteApps.name) },
                        { navController.navigate(AppScreens.ColorSetting.name) },
                        { navController.navigate("debug") },
                        { navController.navigate("password") },
                        { navController.navigate(AppScreens.Home.name) },
                        viewModel.fontSize.collectAsState().value
                    )
                }
                composable(route = "debug") {
                    DebugSettingScreen(
                        changeSetupDoneStatus = {
                            viewModel.toggleSetupDone()
                        },
                        setupStatus = setupStatus!!,
                        isAssistedMode!!,
                        setToAssistedMode = { viewModel.setToAssistedMode() },
                        setToIndividualMode = { viewModel.setToIndividualMode() },
                        increaseFontSize = { viewModel.increaseFontSize() },
                        decreaseFontSize = { viewModel.decreaseFontSize() },
                        fontSize = viewModel.fontSize.collectAsState().value
                    )
                }

                composable(route = "password") {
                    val password by viewModel.getPassword().collectAsState(initial = "")
                    PasswordScreen(onDoneClick = {
                        navController.navigate(AppScreens.Settings.name)
                    }, password)
//                    PasswordSetupScreen(
//                        onDoneClick = { viewModel.setPassword(it) }
//                    )
                }
                composable(route = AppScreens.ColorSetting.name) {
                    ThemeSettingScreen(
                        isThemeDark = isThemeDark!!,
                        { viewModel.changeThemeToDark() },
                        { viewModel.changeThemeToLight() },
                        colorTheme,
                        { viewModel.setThemeColor(it) },
                        { navController.navigate(AppScreens.Home.name) })
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
                    },
                        onClickTerminar = { navController.navigate(AppScreens.Home.name) },
                        isSetup = setupStatus!!)
                }
                composable(route = AppScreens.RemoveApp.name) {

                    RemoveApps(appsProto, onAppClick = { app ->
                        viewModel.removeApp(app.id)
                    }, onClickTerminar = { navController.navigate(AppScreens.Home.name) },
                        viewModel.fontSize.collectAsState().value
                    )
                }
                composable(route = AppScreens.FavoriteApps.name) {

                    SelectFavoriteApps(appsProto, onAppClick = { app ->
                        viewModel.toggleFavorite(app.id)
                    }, onClickTerminar = { navController.navigate(AppScreens.Home.name) },
                        viewModel.fontSize.collectAsState().value
                    )
                }
            }
        }
        //composable de carga
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
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


