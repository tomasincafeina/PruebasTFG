package com.example.pruebastfg.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.R
import com.example.pruebastfg.data.items.ThemeColorPickerItem
import com.example.pruebastfg.data.models.ThemeColorPickerModel
import com.example.pruebastfg.ui.theme.BlueprimaryLight
import com.example.pruebastfg.ui.theme.GreenprimaryLight
import com.example.pruebastfg.ui.theme.PurpleonPrimaryLight
import com.example.pruebastfg.ui.theme.PurpleprimaryLight

@Composable
fun ThemeSettingScreen(
    isThemeDark: Boolean,
    changeThemeToDark: () -> Unit,
    changeThemeToLight: () -> Unit,
    colorTheme: String?,
    setColorTheme: (String) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val colors = listOf(
        "primary" to colorScheme.primary,
        "onPrimary" to colorScheme.onPrimary,
        "primaryContainer" to colorScheme.primaryContainer,
        "onPrimaryContainer" to colorScheme.onPrimaryContainer,
        "inversePrimary" to colorScheme.inversePrimary,
        "secondary" to colorScheme.secondary,
        "onSecondary" to colorScheme.onSecondary,
        "secondaryContainer" to colorScheme.secondaryContainer,
        "onSecondaryContainer" to colorScheme.onSecondaryContainer,
        "tertiary" to colorScheme.tertiary,
        "onTertiary" to colorScheme.onTertiary,
        "tertiaryContainer" to colorScheme.tertiaryContainer,
        "onTertiaryContainer" to colorScheme.onTertiaryContainer,
        "background" to colorScheme.background,
        "onBackground" to colorScheme.onBackground,
        "surface" to colorScheme.surface,
        "onSurface" to colorScheme.onSurface,
        "surfaceVariant" to colorScheme.surfaceVariant,
        "onSurfaceVariant" to colorScheme.onSurfaceVariant,
        "surfaceTint" to colorScheme.surfaceTint,
        "inverseSurface" to colorScheme.inverseSurface,
        "inverseOnSurface" to colorScheme.inverseOnSurface,
        "error" to colorScheme.error,
        "onError" to colorScheme.onError,
        "errorContainer" to colorScheme.errorContainer,
        "onErrorContainer" to colorScheme.onErrorContainer,
        "outline" to colorScheme.outline,
        "outlineVariant" to colorScheme.outlineVariant,
        "scrim" to colorScheme.scrim,
    )

    val colorPickerList = listOf(
        ThemeColorPickerModel("blue", BlueprimaryLight),
        ThemeColorPickerModel("green", GreenprimaryLight),
        ThemeColorPickerModel("purple", PurpleprimaryLight)
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()

    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Card(
                shape = CircleShape,
                colors = if (isThemeDark == true) {
                    androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                } else {
                    androidx.compose.material3.CardDefaults.cardColors()
                }
            ) {
                Box(modifier = Modifier.clickable { changeThemeToLight() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_light_mode_20),
                        contentDescription = "light_mode",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                shape = CircleShape,
                colors = if (isThemeDark == true) {
                    androidx.compose.material3.CardDefaults.cardColors()
                } else {
                    androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                }
            ) {
                Box(modifier = Modifier.clickable { changeThemeToDark() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_dark_mode_20),
                        contentDescription = "dark_mode",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .heightIn(min = 100.dp, max = 300.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorPickerList) { color ->
                ThemeColorPickerItem(
                    color, { setColorTheme(color.themeName) }, colorTheme,
                )
            }
        }
        colors.forEach { (name, color) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color)
                    .padding(8.dp)
            ) {
                Text(text = name, color = if (color.luminance() > 0.5) Color.Black else Color.White)
            }
        }
    }
}
