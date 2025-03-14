package com.example.pruebastfg.data.storage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebastfg.ui.AppViewModel

class ViewModelFactory(
    private val prefsRepo: PreferencesRepository,
    private val appsRepo: AppsProtoRepository,
    private val context: Context // Añadir
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(prefsRepo, appsRepo, context = context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}