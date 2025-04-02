package com.example.pruebastfg.data.storage

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.ContactsContract.Data
import androidx.core.graphics.drawable.toBitmap
import androidx.datastore.core.DataStore
import com.example.pruebastfg.UserApps
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.ui.models.AppModel
import com.example.pruebastfg.ui.utils.toBitmap
import com.example.pruebastfg.ui.utils.toByteString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class AppsProtoRepository(
    private val context: Context,
    private val dataStoreUserApps: DataStore<UserApps>,
) {
    // Obtener la lista de apps con Bitmap
    val userApps: Flow<List<Pair<AppInfo, Bitmap?>>> = dataStoreUserApps.data
        .map { userApps ->
            userApps.appsList
                .map { appInfo ->
                appInfo to appInfo.icon.toBitmap() // Convertir ByteString a Bitmap
            }
                .sortedWith(
                    compareByDescending<Pair<AppInfo, Bitmap?>> { (appInfo, _) -> appInfo.isFavorite } // Ordenar primero por isFavorite (true primero)
                        .thenBy { (appInfo, _) -> appInfo.name } // Luego ordenar por nombre
                )
        }

    // Variable privada para almacenar allApps
    private val _allApps = MutableStateFlow<List<AppModel>>(emptyList())

    // StateFlow público para observar allApps
    val allApps: StateFlow<List<AppModel>> get() = _allApps

    init {
        loadAllApps()
    }

    // Función para cargar allApps
    fun loadAllApps() {
        // Lanzar una corrutina en el contexto de IO (hilo secundario)
        CoroutineScope(Dispatchers.IO).launch {
            val packageManager = context.packageManager
            val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            //llamo a la lista de usuario para ver que apps tiene
            val userAppPackages = dataStoreUserApps.data.map { userApps ->
                userApps.appsList.map { it.packageName }
            }.firstOrNull() ?: emptyList()

            _allApps.value = apps
                //filtro por las apps accesibles por el usuario
                .filter {
                    packageManager.getLaunchIntentForPackage(it.packageName) != null
                }
                //filtro por las que no estan en la lista de usuario
                //no funciona bien.
                // 1o no se actualiza la lista en tiempo real cuando la añades, que es el objetivo
                // 2o para que se actualice tienes que salir y volver a entrar a la app para que se ejecute el init de loadAllApps(),
                .filter {
                    // Exclude apps that are in userApps
                    !userAppPackages.contains(it.packageName)
                }
                .map {
                    AppModel(
                        name = it.loadLabel(packageManager).toString(),
                        icon = it.loadIcon(packageManager).toBitmap(),
                        packageName = it.packageName
                    )
                }.sortedBy { it.name }
        }
    }

    suspend fun addApp(name: String, packageName: String, icon: Bitmap) {
        // Lanzar una corrutina en el contexto de IO (hilo secundario)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreUserApps.updateData { current ->
                // Verificar si la app ya existe en la lista
                val appExists = current.appsList.any { it.packageName == packageName }

                if (appExists) {
                    // Si la app ya existe, no la añadimos y devolvemos la lista actual
                    current
                } else {
                    // Si la app no existe, la añadimos a la lista
                    val newApp = AppInfo.newBuilder()
                        .setId(UUID.randomUUID().toString())
                        .setName(name)
                        .setIcon(icon.toByteString()) // Convertir Bitmap a ByteString
                        .setPackageName(packageName)
                        .setIsFavorite(false)
                        .build()

                    current.toBuilder().addApps(newApp).build()
                }
            }
        }
    }

    suspend fun toggleFavorite(appId: String) {
        dataStoreUserApps.updateData { current ->
            val updatedApps = current.appsList.map { app ->
                if (app.id == appId) app.toBuilder().setIsFavorite(!app.isFavorite).build()
                else app
            }
            current.toBuilder().clearApps().addAllApps(updatedApps).build()
        }
    }

    fun getApp(appId: String): Flow<AppInfo?> {
        return dataStoreUserApps.data.map { userApps ->
            userApps.appsList.find { it.id == appId }
        }
    }
//no pude guarpor nombre, no me dejaba
    suspend fun removeApp(appId: String) {
        // Lanzar una corrutina en el contexto de IO (hilo secundario)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreUserApps.updateData { current ->
                current.toBuilder().removeApps(
                    current.appsList.indexOfFirst { it.id == appId }
                ).build()
            }
        }
    }


}