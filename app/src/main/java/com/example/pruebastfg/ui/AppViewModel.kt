package com.example.pruebastfg.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
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
    val isAssistedMode: Flow<Boolean?> = prefsRepo.getIsAssistedMode()
    val isHighContrast: Flow<Boolean?> = prefsRepo.getHighContrast()

    fun getHighContrast(): Flow<Boolean?> {
        return prefsRepo.getHighContrast()
    }

    fun setHighContrastTrue() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setHighContrastTrue(true)
        }
    }
    fun setHighContrastFalse() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setHighContrastFalse(false)
        }
    }
    fun toogleHighContrast() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.toogleHighContrast()
        }
    }

    fun setToIndividualMode() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setToIndividualMode(true)
        }
    }
    fun setToAssistedMode() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setToAssistedMode(true)
        }
    }
    // Estado mutable usando MutableState
    private val _fontSize: MutableStateFlow<TextUnit> = MutableStateFlow(25.sp)

    // Exponemos el estado como State (solo lectura)
    val fontSize: StateFlow<TextUnit> = _fontSize

    // Minimum and maximum font sizes
    private val minSize = 20.sp
    private val maxSize = 42.5.sp
    private val stepSize = 2.5.sp

    fun increaseFontSize() {
        // Forma correcta de incrementar en Kotlin
        val newSize = _fontSize.value.value + stepSize.value
        _fontSize.value = newSize.sp

        // Limitar al máximo
        if (_fontSize.value > maxSize) {
            _fontSize.value = maxSize
        }
    }

    fun decreaseFontSize() {
        // Forma correcta de decrementar en Kotlin
        val newSize = _fontSize.value.value - stepSize.value
        _fontSize.value = newSize.sp

        // Limitar al mínimo
        if (_fontSize.value < minSize) {
            _fontSize.value = minSize
        }
    }
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
    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(pwd = password)
    }

    fun getPassword(): Flow<String> {
        return prefsRepo.getPassword()
    }

    fun setPassword(password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setPassword(password)
        }
    }

    // Métodos para Preferences DataStore
    fun setThemeColor(themeColor: String) {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.setThemeColor(themeColor)
        }
    }

    fun changeThemeToDark() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.changeThemeToDark()
        }
    }

    fun changeThemeToLight() {
        viewModelScope.launch(Dispatchers.IO) {
            prefsRepo.changeThemeToLight()
        }
    }

    // Métodos para Preferences DataStore
    fun saveUserName(name: String, context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
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
        val bitmap =
            Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }

}
