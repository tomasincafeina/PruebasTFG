package com.example.pruebastfg.ui.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingModel(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)
