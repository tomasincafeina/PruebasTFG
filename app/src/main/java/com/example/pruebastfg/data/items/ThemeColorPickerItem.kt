package com.example.pruebastfg.data.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.data.models.ThemeColorPickerModel


@Composable
fun ThemeColorPickerItem(
    item: ThemeColorPickerModel,
    onClick: () -> Unit,
    colorTheme: String?,
) {
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = item.primaryColor ?: Color.Transparent
        ),
        modifier = Modifier
            .aspectRatio(1f)
            .border(
                width = if (colorTheme == item.themeName) 2.dp else 2.dp,
                color = if (colorTheme == item.themeName) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    item.primaryColor?.copy(alpha = 0.3f) ?: Color.Transparent
                },
                shape = CircleShape
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape) // ¡Esto hace el área clickable circular!
                .clickable(onClick = onClick)
        ) {
            // Contenido adicional si lo necesitas
//            if (colorTheme == item.themeName) {
//                Icon(
//                    imageVector = Icons.Default.Check,
//                    contentDescription = "Selected",
//                    modifier = Modifier.align(Alignment.Center),
//                    //tint = if (item.primaryColor?.isLight() == true) Color.Black else Color.White
//                )
//            }
        }
    }

}