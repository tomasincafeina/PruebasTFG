package com.example.pruebastfg.ui.data.storage

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import com.example.pruebastfg.UserApps
import com.example.pruebastfg.AppInfo
import com.example.pruebastfg.ui.utils.toBitmap
import com.example.pruebastfg.ui.utils.toByteString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class AppsProtoRepository(
    private val dataStoreUserApps: DataStore<UserApps>,
) {

    // Obtener la lista de apps con Bitmap
    val userApps: Flow<List<Pair<AppInfo, Bitmap?>>> = dataStoreUserApps.data
        .map { userApps ->
            userApps.appsList.map { appInfo ->
                appInfo to appInfo.icon.toBitmap() // Convertir ByteString a Bitmap
            }
        }

    suspend fun addApp(name: String, packageName: String, icon: Bitmap) {
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

    suspend fun removeApp(appId: String) {
        dataStoreUserApps.updateData { current ->
            current.toBuilder().removeApps(
                current.appsList.indexOfFirst { it.id == appId }
            ).build()
        }
    }


}