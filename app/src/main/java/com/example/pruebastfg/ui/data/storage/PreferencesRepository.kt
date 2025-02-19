package com.example.pruebastfg.ui.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "settings")

class PreferencesRepository(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
    }

    // Guardar el nombre de usuario
    suspend fun saveUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
        }
    }

    // Obtener el nombre de usuario
    fun getUserName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME_KEY]
        }
    }
}