package com.example.pruebastfg.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingModel(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)
