package com.example.pruebastfg.ui
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebastfg.ui.data.AppUiState
import com.example.pruebastfg.ui.models.AppModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun getInstalledApps(context: Context): List<AppModel> {
        val packageManager = context.packageManager
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

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

        return apps.filter { app ->
             app.packageName in essentialPackages
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