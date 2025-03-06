package com.example.pruebastfg.ui.data.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebastfg.ui.AppViewModel

class ViewModelFactory(
    private val prefsRepo: PreferencesRepository,
    private val appsRepo: AppsProtoRepository // Añadir
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(prefsRepo, appsRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}