package com.example.pruebastfg.data.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingModel(
    @StringRes val title: Int,
    val icon: ImageVector,
    val onClick: () -> Unit
)
