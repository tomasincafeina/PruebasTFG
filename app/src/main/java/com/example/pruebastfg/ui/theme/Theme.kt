package com.example.pruebastfg.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pruebastfg.ui.AppViewModel

private val BlueDarkColorScheme = darkColorScheme(
    primary = BlueprimaryDark,
    onPrimary = BlueonPrimaryDark,
    primaryContainer = BlueprimaryContainerDark,
    onPrimaryContainer = BlueonPrimaryContainerDark,
    secondary = BluesecondaryDark,
    onSecondary = BlueonSecondaryDark,
    secondaryContainer = BluesecondaryContainerDark,
    onSecondaryContainer = BlueonSecondaryContainerDark,
    tertiary = BluetertiaryDark,
    onTertiary = BlueonTertiaryDark,
    tertiaryContainer = BluetertiaryContainerDark,
    onTertiaryContainer = BlueonTertiaryContainerDark,
    error = BlueerrorDark,
    onError = BlueonErrorDark,
    errorContainer = BlueerrorContainerDark,
    onErrorContainer = BlueonErrorContainerDark,
    background = BluebackgroundDark,
    onBackground = BlueonBackgroundDark,
    surface = BluesurfaceDark,
    onSurface = BlueonSurfaceDark,
    surfaceVariant = BluesurfaceVariantDark,
    onSurfaceVariant = BlueonSurfaceVariantDark,
    outline = BlueoutlineDark,
    outlineVariant = BlueoutlineVariantDark,
    scrim = BluescrimDark,
    inverseSurface = BlueinverseSurfaceDark,
    inverseOnSurface = BlueinverseOnSurfaceDark,
    inversePrimary = BlueinversePrimaryDark,
    surfaceDim = BluesurfaceDimDark,
    surfaceBright = BluesurfaceBrightDark,
    surfaceContainerLowest = BluesurfaceContainerLowestDark,
    surfaceContainerLow = BluesurfaceContainerLowDark,
    surfaceContainer = BluesurfaceContainerDark,
    surfaceContainerHigh = BluesurfaceContainerHighDark,
    surfaceContainerHighest = BluesurfaceContainerHighestDark,
    )
private val BlueLightColorScheme = lightColorScheme(
    primary = BlueprimaryLight,
    onPrimary = BlueonPrimaryLight,
    primaryContainer = BlueprimaryContainerLight,
    onPrimaryContainer = BlueonPrimaryContainerLight,
    secondary = BluesecondaryLight,
    onSecondary = BlueonSecondaryLight,
    secondaryContainer = BluesecondaryContainerLight,
    onSecondaryContainer = BlueonSecondaryContainerLight,
    tertiary = BluetertiaryLight,
    onTertiary = BlueonTertiaryLight,
    tertiaryContainer = BluetertiaryContainerLight,
    onTertiaryContainer = BlueonTertiaryContainerLight,
    error = BlueerrorLight,
    onError = BlueonErrorLight,
    errorContainer = BlueerrorContainerLight,
    onErrorContainer = BlueonErrorContainerLight,
    background = BluebackgroundLight,
    onBackground = BlueonBackgroundLight,
    surface = BluesurfaceLight,
    onSurface = BlueonSurfaceLight,
    surfaceVariant = BluesurfaceVariantLight,
    onSurfaceVariant = BlueonSurfaceVariantLight,
    outline = BlueoutlineLight,
    outlineVariant = BlueoutlineVariantLight,
    scrim = BluescrimLight,
    inverseSurface = BlueinverseSurfaceLight,
    inverseOnSurface = BlueinverseOnSurfaceLight,
    inversePrimary = BlueinversePrimaryLight,
    surfaceDim = BluesurfaceDimLight,
    surfaceBright = BluesurfaceBrightLight,
    surfaceContainerLowest = BluesurfaceContainerLowestLight,
    surfaceContainerLow = BluesurfaceContainerLowLight,
    surfaceContainer = BluesurfaceContainerLight,
    surfaceContainerHigh = BluesurfaceContainerHighLight,
    surfaceContainerHighest = BluesurfaceContainerHighestLight,
    )
private val GreenDarkColorScheme = darkColorScheme(
    primary = GreenprimaryDark,
    onPrimary = GreenonPrimaryDark,
    primaryContainer = GreenprimaryContainerDark,
    onPrimaryContainer = GreenonPrimaryContainerDark,
    secondary = GreensecondaryDark,
    onSecondary = GreenonSecondaryDark,
    secondaryContainer = GreensecondaryContainerDark,
    onSecondaryContainer = GreenonSecondaryContainerDark,
    tertiary = GreentertiaryDark,
    onTertiary = GreenonTertiaryDark,
    tertiaryContainer = GreentertiaryContainerDark,
    onTertiaryContainer = GreenonTertiaryContainerDark,
    error = GreenerrorDark,
    onError = GreenonErrorDark,
    errorContainer = GreenerrorContainerDark,
    onErrorContainer = GreenonErrorContainerDark,
    background = GreenbackgroundDark,
    onBackground = GreenonBackgroundDark,
    surface = GreensurfaceDark,
    onSurface = GreenonSurfaceDark,
    surfaceVariant = GreensurfaceVariantDark,
    onSurfaceVariant = GreenonSurfaceVariantDark,
    outline = GreenoutlineDark,
    outlineVariant = GreenoutlineVariantDark,
    scrim = GreenscrimDark,
    inverseSurface = GreeninverseSurfaceDark,
    inverseOnSurface = GreeninverseOnSurfaceDark,
    inversePrimary = GreeninversePrimaryDark,
    surfaceDim = GreensurfaceDimDark,
    surfaceBright = GreensurfaceBrightDark,
    surfaceContainerLowest = GreensurfaceContainerLowestDark,
    surfaceContainerLow = GreensurfaceContainerLowDark,
    surfaceContainer = GreensurfaceContainerDark,
    surfaceContainerHigh = GreensurfaceContainerHighDark,
    surfaceContainerHighest = GreensurfaceContainerHighestDark,
)
private val GreenLightColorScheme = lightColorScheme(
    primary = GreenprimaryLight,
    onPrimary = GreenonPrimaryLight,
    primaryContainer = GreenprimaryContainerLight,
    onPrimaryContainer = GreenonPrimaryContainerLight,
    secondary = GreensecondaryLight,
    onSecondary = GreenonSecondaryLight,
    secondaryContainer = GreensecondaryContainerLight,
    onSecondaryContainer = GreenonSecondaryContainerLight,
    tertiary = GreentertiaryLight,
    onTertiary = GreenonTertiaryLight,
    tertiaryContainer = GreentertiaryContainerLight,
    onTertiaryContainer = GreenonTertiaryContainerLight,
    error = GreenerrorLight,
    onError = GreenonErrorLight,
    errorContainer = GreenerrorContainerLight,
    onErrorContainer = GreenonErrorContainerLight,
    background = GreenbackgroundLight,
    onBackground = GreenonBackgroundLight,
    surface = GreensurfaceLight,
    onSurface = GreenonSurfaceLight,
    surfaceVariant = GreensurfaceVariantLight,
    onSurfaceVariant = GreenonSurfaceVariantLight,
    outline = GreenoutlineLight,
    outlineVariant = GreenoutlineVariantLight,
    scrim = GreenscrimLight,
    inverseSurface = GreeninverseSurfaceLight,
    inverseOnSurface = GreeninverseOnSurfaceLight,
    inversePrimary = GreeninversePrimaryLight,
    surfaceDim = GreensurfaceDimLight,
    surfaceBright = GreensurfaceBrightLight,
    surfaceContainerLowest = GreensurfaceContainerLowestLight,
    surfaceContainerLow = GreensurfaceContainerLowLight,
    surfaceContainer = GreensurfaceContainerLight,
    surfaceContainerHigh = GreensurfaceContainerHighLight,
    surfaceContainerHighest = GreensurfaceContainerHighestLight,
)

@Composable
fun PruebasTFGTheme(
    viewModel: AppViewModel,
    content: @Composable () -> Unit
) {
    val isThemeDark = viewModel.isThemeDark.collectAsState(initial = null)
    val colorTheme = viewModel.colorTheme.collectAsState(initial = "blue")

    //val colors = if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
    val colors = when (colorTheme.value) {
        "blue" -> if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
        "green" -> if (isThemeDark.value == true) GreenDarkColorScheme else GreenLightColorScheme
        else -> if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}