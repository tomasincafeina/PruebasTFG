package com.example.pruebastfg.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.data.AppUiState
import com.example.pruebastfg.data.storage.AppsProtoRepository
import com.example.pruebastfg.ui.models.AppModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.pruebastfg.data.storage.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//private val Context.dataStore by preferencesDataStore(name = "settings")

class AppViewModel(
    private val prefsRepo: PreferencesRepository,
    private val appsRepo: AppsProtoRepository,
    context: Context
) : ViewModel() {


    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    // Datos de Preferences
    val userName: Flow<String?> = prefsRepo.getUserName()
    val setupDone: Flow<Boolean?> = prefsRepo.getSetupStatus()
    val isThemeDark: Flow<Boolean?> = prefsRepo.isThemeDark()
    val colorTheme: Flow<String?> = prefsRepo.getThemeColor()

    // Datos de Proto DataStore
    val apps: Flow<List<Pair<AppInfo, Bitmap?>>> = appsRepo.userApps

    val allApps: StateFlow<List<AppModel>> get() = appsRepo.allApps

    val startDestination: StateFlow<String> = setupDone
        .map { isSetupDone ->
            if (isSetupDone == false) AppScreens.Setup.name else AppScreens.Home.name
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AppScreens.Home.name)


    // Lista de paquetes esenciales que queremos incluir
    val essentialPackages = listOf(
        "com.android.dialer",         // Teléfono
        "com.android.contacts",       // Contactos
        "com.google.android.dialer",  // Teléfono (Google)
        "com.google.android.contacts", // Contactos (Google)
        "com.android.messaging",      // Mensajes
        "com.google.android.apps.messaging", // Mensajes (Google)
        "com.android.documentsui",    // Archivos
        "com.google.android.documentsui",    // Archivos(Google)
        "com.google.android.deskclock",   // Reloj (Google)
        "com.android.deskclock",      // Reloj (AOSP)
    )

    // Métodos para Preferences DataStore
    fun setThemeColor(themeColor: String) {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setThemeColor(themeColor)
        }
    }

    fun toggleTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.toggleTheme()
        }
    }
    // Métodos para Preferences DataStore
    fun saveUserName(name: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.saveUserName(name, context)
        }
    }

    fun toggleSetupDone() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setSetupDoneContrario()
        }
    }
    // Métodos para Proto DataStore
    fun addApp(name: String, packageName: String, icon: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            appsRepo.addApp(name, packageName, icon)
        }
    }

    fun addAppXX(name: String, packageName: String, icon: Bitmap): Boolean {
        var result = false
        viewModelScope.launch(Dispatchers.IO) {
            result = try {
                appsRepo.addApp(name, packageName, icon)
                true // App añadida correctamente
            } catch (e: Exception) {
                false // Error al añadir la app (ya existe o otro error)
            }
        }
        return result
    }

    fun toogleIsSelected(app: AppModel) {
        app.isSelected = !app.isSelected
    }

    fun toggleFavorite(appId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appsRepo.toggleFavorite(appId)
        }
    }

    fun removeApp(appId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appsRepo.removeApp(appId)
        }
    }

    fun updateUserName(userName: String) {
        _uiState.value = _uiState.value.copy(userName = userName)
    }

//ya no lo uso
//    fun getEssencialApps(context: Context): List<AppModel> {
//        val packageManager = context.packageManager
//        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//
//        return apps.filter { app ->
//            app.packageName in essentialPackages
//        }
//            .map {
//                AppModel(
//                    name = it.loadLabel(packageManager).toString(),
//                    icon = it.loadIcon(packageManager).toBitmap(),
//                    packageName = it.packageName,
//                    isSelected = false
//                )
//            }
//    }

//ya no lo uso, uso directamente el de proto datastorage
//    fun getAllApps(context: Context): List<AppModel> {
//        val packageManager = context.packageManager
//        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//
//        return apps.filter {
//            packageManager.getLaunchIntentForPackage(it.packageName) != null
//        }
//            .map {
//                AppModel(
//                    name = it.loadLabel(packageManager).toString(),
//                    icon = it.loadIcon(packageManager).toBitmap(),
//                    packageName = it.packageName
//                )
//            }
//    }
//
//    fun logInstalledApps(context: Context) {
//        val packageManager = context.packageManager
//        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//
//        apps.forEach { app ->
//            Log.d("InstalledApps", "${app.loadLabel(packageManager)} - ${app.packageName}")
//        }
//    }

    fun Drawable.toBitmap(): Bitmap {
        if (this is BitmapDrawable) {
            return bitmap
        }
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }
}
