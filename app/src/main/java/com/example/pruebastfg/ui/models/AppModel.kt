package com.example.pruebastfg.ui.models

import android.graphics.Bitmap

data class AppModel(
    val name: String,
    val icon: Bitmap, // Usamos Bitmap para el icono
    val packageName: String
)
