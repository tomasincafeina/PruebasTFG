package com.example.pruebastfg.ui

import android.graphics.Bitmap
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.data.storage.AppsProtoRepository
import com.example.pruebastfg.ui.data.storage.appsDataStore
import com.example.pruebastfg.ui.data.storage.ViewModelFactory
import com.example.pruebastfg.ui.data.storage.PreferencesRepository
import com.example.pruebastfg.ui.screens.setup.UserNameSetupScreen
import com.example.pruebastfg.ui.screens.setup.WelcomeSetupScreen


enum class AppScreens(@StringRes val title: Int) {
    Setup(title = R.string.setup), Home(title = R.string.home), AddApp(title = R.string.addapps), RemoveApp(
        title = R.string.removeapps
    ),
    FavoriteApps(title = R.string.favoriteapps), Configuration(title = R.string.configuration)
}

enum class SetupSubScreens(@StringRes val title: Int) {
    welcome(title = R.string.welcome), username(title = R.string.username), modecolor(title = R.string.modecolor), fontsize(
        title = R.string.fontsize
    ),
    launcher(title = R.string.launcher)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,

    topBarTitle: String
) {
    // Usamos CenterAlignedTopAppBar para centrar el título correctamente
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topBarTitle, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold
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
    onAppClick: (String) -> Unit, navController: NavHostController = rememberNavController()
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
    val prefsRepo = remember { PreferencesRepository(context) }
    val appsRepo = remember { AppsProtoRepository(context.appsDataStore) }

    val viewModel: AppViewModel = viewModel(factory = ViewModelFactory(prefsRepo, appsRepo))
    // Observar datos
    val userName by viewModel.userName.collectAsState(initial = "")
//    val apps by viewModel.apps.collectAsState(initial = emptyList())
    val apps = viewModel.getEssencialApps(context)
    val allApps = viewModel.getAllApps(context)

    val appsProto by viewModel.apps.collectAsState(initial = emptyList())

    val uiState by viewModel.uiState.collectAsState()
    val setupStatus by prefsRepo.getSetupStatus().collectAsState(initial = null)

    Scaffold(containerColor = MaterialTheme.colorScheme.surface, topBar = {
        AppTopBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.popBackStack() },
            modifier = Modifier,
            topBarTitle = if (currentScreen == AppScreens.Home) {
                "Hola, $userName!"
            } else {
                currentScreen.name
            }
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
            navController = navController, startDestination = if (setupStatus == false) {
                AppScreens.Setup.name
            } else {
                AppScreens.Home.name
            }, modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Pantalla de Setup
            composable(route = AppScreens.Setup.name) {
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
            composable(route = AppScreens.AddApp.name) {
                AllAppsListScreen(apps = allApps, onAppClick = { app ->
                    val appExists = appsProto.any { it.first.packageName == app.packageName }

                    if (!appExists) {
                        viewModel.addAppXX(
                            name = app.name, packageName = app.packageName, icon = app.icon
                        )
                        Toast.makeText(context, "Añadiendo ${app.name}...", Toast.LENGTH_SHORT)
                            .show()

                        navController.navigate(AppScreens.Home.name)
                    } else {
                        Toast.makeText(
                            context, "${app.name} ya está en la lista", Toast.LENGTH_SHORT
                        ).show()
                    }
                }, onClickTerminar = { navController.navigate(AppScreens.Home.name) })
            }
            composable(route = AppScreens.RemoveApp.name) {
                RemoveAppsFromProto(appsProto, onAppClick = { app ->
                    viewModel.removeApp(app.id)
                }, onClickTerminar = { navController.navigate(AppScreens.Home.name) })
            }
            composable(route = AppScreens.FavoriteApps.name) {
                FavoriteAppsFromProto(appsProto, onAppClick = { app ->
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
fun AllAppsListScreen(
    apps: List<AppModel>, onAppClick: (AppModel) -> Unit, onClickTerminar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
            items(apps) { app ->
                AppItem(app = app, onClick = { onAppClick(app) })
            }
        }
        ElevatedCard(
            onClick = onClickTerminar, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(2.dp, MaterialTheme.colorScheme.outline, shape = Shapes().large)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.terminar),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
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
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = app.icon.asImageBitmap(),
                contentDescription = app.name,
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = app.name, fontSize = 20.sp, maxLines = 2, textAlign = TextAlign.Center,
                )
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
            modifier = Modifier
        )/* Botón para navegar a la pantalla de todas las apps */
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Botón para cambiar el estado del setup
            Button(
                onClick = changeSetupDoneStatus, modifier = Modifier.weight(1f)
            ) {
                Text(text = "Setup")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = goToAddApp,modifier = Modifier.weight(1f)

            ) {
                Text(text = "Añadir app")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = goToRemoveApps,modifier = Modifier.weight(1f)
            ) {
                Text(text = "Borrar app")
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Button(
                onClick = goToFavoriteApps,modifier = Modifier.weight(1f)
            ) {
                Text(text = "Favoritas")
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


@Composable
fun RemoveAppsFromProto(
    apps: List<Pair<AppInfo, Bitmap?>>, // Lista de aplicaciones con sus iconos
    onAppClick: (AppInfo) -> Unit, // Callback cuando se hace clic en una app
    onClickTerminar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
            items(apps) { (appInfo, bitmap) ->
                AppItemProto(
                    appInfo = appInfo,
                    bitmap = bitmap,
                    onClick = { onAppClick(appInfo) })
            }
        }
        ElevatedCard(
            onClick = onClickTerminar, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(2.dp, MaterialTheme.colorScheme.outline, shape = Shapes().large)

        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.terminar),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Composable
fun AppItemProto(
    appInfo: AppInfo, bitmap: Bitmap?, onClick: () -> Unit
) {
//    val bitmap = remember(appInfo.icon) {
//        appInfo.icon?.toBitmap()
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Make the card square
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = appInfo.name,
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .run {
                        if (appInfo.isFavorite == true) {
                            padding(horizontal = 10.dp)
                        } else {
                            this
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                    Spacer(modifier = Modifier.width(32.dp))


                Text(
                    modifier = Modifier.weight(1f),
                    text = appInfo.name,
                    fontSize = 20.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
                // Espacio reservado para el ícono de la estrella
                Box(
                    modifier = Modifier.size(30.dp), // Tamaño fijo para el ícono
                    contentAlignment = Alignment.Center
                ) {
                    if (appInfo.isFavorite == true) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrella",
                            modifier = Modifier.size(30.dp),
                            tint = Color(android.graphics.Color.parseColor("#f5cb42")),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteAppsFromProto(
    apps: List<Pair<AppInfo, Bitmap?>>, // Lista de aplicaciones con sus iconos
    onAppClick: (AppInfo) -> Unit, // Callback cuando se hace clic en una app
    onClickTerminar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
            items(apps) { (appInfo, bitmap) ->
                AppItemProto(
                    appInfo = appInfo,
                    bitmap = bitmap,
                    onClick = { onAppClick(appInfo) })
            }
        }
        ElevatedCard(
            onClick = onClickTerminar, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.terminar),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}