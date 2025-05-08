package com.example.pruebastfg.data.storage

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//https://www.youtube.com/watch?v=9x_FmyLkDZ8&ab_channel=Programaci%C3%B3nAndroidbyAristiDevs
//https://developer.android.com/codelabs/basic-android-kotlin-training-preferences-datastore?hl=es-419#0

//Preferences DataStore accede a los datos y los almacena en función de claves, sin definir un esquema (modelo de base de datos) por adelantado.
//Diferencias entre Room y Datastore: cuándo usarlos
//Si tu aplicación necesita almacenar datos grandes o complejos en un formato estructurado, como SQL, considera usar Room. Sin embargo, si solo necesitas almacenar
//cantidades simples o pequeñas de datos que se pueden guardar en pares clave-valor, DataStore es ideal.

private val Context.dataStore by preferencesDataStore(name = "settings")

class PreferencesRepository(context: Context) {
    private val dataStore = context.dataStore

    //aqui creamos los objetos que vamos a guardar
    companion object {
        // Use a more descriptive name for the key and make it a constant
        private val USER_NAME_KEY =
            androidx.datastore.preferences.core.stringPreferencesKey("user_name")
        private val SETUP_DONE_KEY =
            androidx.datastore.preferences.core.booleanPreferencesKey("setup_done")
        private val THEME_MODE_KEY =
            androidx.datastore.preferences.core.booleanPreferencesKey("theme_mode")
        private val THEME_COLOR_KEY =
            androidx.datastore.preferences.core.stringPreferencesKey("theme_color")
        private val PASSWORD_KEY =
            androidx.datastore.preferences.core.stringPreferencesKey("password")
        private val IS_ASSITED_MODE_KEY =
            androidx.datastore.preferences.core.booleanPreferencesKey("is_assisted_mode")
    }

    fun getIsAssistedMode(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[IS_ASSITED_MODE_KEY] ?: true
        }
    }

    suspend fun setToIndividualMode(isAssistedMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ASSITED_MODE_KEY] = false
        }
    }

    suspend fun setToAssistedMode(isAssistedMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ASSITED_MODE_KEY] = true
        }
    }

    fun getPassword(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PASSWORD_KEY] ?: ""
        }
    }

    suspend fun setPassword(password: String) {
        dataStore.edit { preferences ->
            preferences[PASSWORD_KEY] = password
        }
    }

    fun getThemeColor(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[THEME_COLOR_KEY] ?: "blue"
        }
    }

    suspend fun setThemeColor(themeColor: String) {
        dataStore.edit { preferences ->
            preferences[THEME_COLOR_KEY] = themeColor
        }
    }

    fun isThemeDark(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[THEME_MODE_KEY] ?: true
        }
    }

    suspend fun changeThemeToDark() {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = true
        }
    }

    suspend fun changeThemeToLight() {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = false
        }
    }

    /**
     * Saves the user's name to DataStore.
     *
     * @param userName The user's name to save.
     */
    suspend fun saveUserName(userName: String, context: Context) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
        }
    }

    /**
     * Retrieves the user's name from DataStore.
     *
     * @return A Flow emitting the user's name, or null if not found.
     */
    fun getUserName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME_KEY] ?: ""
        }
    }

    /**
     * Clears the user's name from DataStore.
     */
//        suspend fun clearUserName() {
//            dataStore.edit { preferences ->
//                preferences.remove(USER_NAME_KEY)
//            }
//        }

    fun getSetupStatus(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[SETUP_DONE_KEY] ?: false
        }
    }

    suspend fun setSetupDone() {
        dataStore.edit { preferences ->
            preferences[SETUP_DONE_KEY] = true
        }
    }

    suspend fun setSetupDoneContrario() {
        dataStore.edit { preferences ->
            val currentStatus = preferences[SETUP_DONE_KEY] ?: false
            preferences[SETUP_DONE_KEY] = !currentStatus

        }
    }

}
