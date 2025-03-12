package com.example.pruebastfg.ui

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.ui.data.AppUiState
import com.example.pruebastfg.ui.data.storage.AppsProtoRepository
import com.example.pruebastfg.ui.models.AppModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.pruebastfg.ui.data.storage.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//private val Context.dataStore by preferencesDataStore(name = "settings")

class AppViewModel(
    private val prefsRepo: PreferencesRepository,
    private val appsRepo: AppsProtoRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    // Datos de Preferences
    val userName: Flow<String?> = prefsRepo.getUserName()
    val setupDone: Flow<Boolean?> = prefsRepo.getSetupStatus()

    // Datos de Proto DataStore
    val apps: Flow<List<Pair<AppInfo, Bitmap?>>> = appsRepo.userApps

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
    fun saveUserName(name: String, context: Context) {
        viewModelScope.launch {
            prefsRepo.saveUserName(name, context)
        }
    }

    fun toggleSetupDone() {
        viewModelScope.launch {
            prefsRepo.setSetupDoneContrario()
        }
    }

    // Métodos para Proto DataStore
    fun addApp(name: String, packageName: String, icon: Bitmap) {
        viewModelScope.launch {
            appsRepo.addApp(name, packageName, icon)
        }
    }

    fun addAppXX(name: String, packageName: String, icon: Bitmap): Boolean {
        var result = false
        viewModelScope.launch {
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
        viewModelScope.launch {
            appsRepo.toggleFavorite(appId)
        }
    }

    fun removeApp(appId: String) {
        viewModelScope.launch {
            appsRepo.removeApp(appId)
        }
    }

    fun updateUserName(userName: String) {
        _uiState.value = _uiState.value.copy(userName = userName)
    }


    fun getEssencialApps(context: Context): List<AppModel> {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        return apps.filter { app ->
            app.packageName in essentialPackages
        }
            .map {
                AppModel(
                    name = it.loadLabel(packageManager).toString(),
                    icon = it.loadIcon(packageManager).toBitmap(),
                    packageName = it.packageName,
                    isSelected = false
                )
            }
    }


    fun getAllApps(context: Context): List<AppModel> {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        return apps.filter {
            packageManager.getLaunchIntentForPackage(it.packageName) != null
        }
            .map {
                AppModel(
                    name = it.loadLabel(packageManager).toString(),
                    icon = it.loadIcon(packageManager).toBitmap(),
                    packageName = it.packageName
                )
            }
    }

    fun logInstalledApps(context: Context) {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        apps.forEach { app ->
            Log.d("InstalledApps", "${app.loadLabel(packageManager)} - ${app.packageName}")
        }
    }

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
