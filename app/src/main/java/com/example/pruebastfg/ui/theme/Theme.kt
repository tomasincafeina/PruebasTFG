package com.example.pruebastfg.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
private val PurpleDarkColorScheme = darkColorScheme(
    primary = PurpleprimaryDark,
    onPrimary = PurpleonPrimaryDark,
    primaryContainer = PurpleprimaryContainerDark,
    onPrimaryContainer = PurpleonPrimaryContainerDark,
    secondary = PurplesecondaryDark,
    onSecondary = PurpleonSecondaryDark,
    secondaryContainer = PurplesecondaryContainerDark,
    onSecondaryContainer = PurpleonSecondaryContainerDark,
    tertiary = PurpletertiaryDark,
    onTertiary = PurpleonTertiaryDark,
    tertiaryContainer = PurpletertiaryContainerDark,
    onTertiaryContainer = PurpleonTertiaryContainerDark,
    error = PurpleerrorDark,
    onError = PurpleonErrorDark,
    errorContainer = PurpleerrorContainerDark,
    onErrorContainer = PurpleonErrorContainerDark,
    background = PurplebackgroundDark,
    onBackground = PurpleonBackgroundDark,
    surface = PurplesurfaceDark,
    onSurface = PurpleonSurfaceDark,
    surfaceVariant = PurplesurfaceVariantDark,
    onSurfaceVariant = PurpleonSurfaceVariantDark,
    outline = PurpleoutlineDark,
    outlineVariant = PurpleoutlineVariantDark,
    scrim = PurplescrimDark,
    inverseSurface = PurpleinverseSurfaceDark,
    inverseOnSurface = PurpleinverseOnSurfaceDark,
    inversePrimary = PurpleinversePrimaryDark,
    surfaceDim = PurplesurfaceDimDark,
    surfaceBright = PurplesurfaceBrightDark,
    surfaceContainerLowest = PurplesurfaceContainerLowestDark,
    surfaceContainerLow = PurplesurfaceContainerLowDark,
    surfaceContainer = PurplesurfaceContainerDark,
    surfaceContainerHigh = PurplesurfaceContainerHighDark,
    surfaceContainerHighest = PurplesurfaceContainerHighestDark,
)
val PurpleLightColorScheme = lightColorScheme(
    primary = PurpleprimaryLight,
    onPrimary = PurpleonPrimaryLight,
    primaryContainer = PurpleprimaryContainerLight,
    onPrimaryContainer = PurpleonPrimaryContainerLight,
    secondary = PurplesecondaryLight,
    onSecondary = PurpleonSecondaryLight,
    secondaryContainer = PurplesecondaryContainerLight,
    onSecondaryContainer = PurpleonSecondaryContainerLight,
    tertiary = PurpletertiaryLight,
    onTertiary = PurpleonTertiaryLight,
    tertiaryContainer = PurpletertiaryContainerLight,
    onTertiaryContainer = PurpleonTertiaryContainerLight,
    error = PurpleerrorLight,
    onError = PurpleonErrorLight,
    errorContainer = PurpleerrorContainerLight,
    onErrorContainer = PurpleonErrorContainerLight,
    background = PurplebackgroundLight,
    onBackground = PurpleonBackgroundLight,
    surface = PurplesurfaceLight,
    onSurface = PurpleonSurfaceLight,
    surfaceVariant = PurplesurfaceVariantLight,
    onSurfaceVariant = PurpleonSurfaceVariantLight,
    outline = PurpleoutlineLight,
    outlineVariant = PurpleoutlineVariantLight,
    scrim = PurplescrimLight,
    inverseSurface = PurpleinverseSurfaceLight,
    inverseOnSurface = PurpleinverseOnSurfaceLight,
    inversePrimary = PurpleinversePrimaryLight,
    surfaceDim = PurplesurfaceDimLight,
    surfaceBright = PurplesurfaceBrightLight,
    surfaceContainerLowest = PurplesurfaceContainerLowestLight,
    surfaceContainerLow = PurplesurfaceContainerLowLight,
    surfaceContainer = PurplesurfaceContainerLight,
    surfaceContainerHigh = PurplesurfaceContainerHighLight,
    surfaceContainerHighest = PurplesurfaceContainerHighestLight,
)
val HighContrastDarkColorScheme = darkColorScheme(
    surface = HighContrastBlack,
    secondaryContainer = HighContrastWhite,
    primaryContainer = HighContrastWhite,
    onPrimaryContainer = HighContrastWhite,
    onBackground = HighContrastBlack

)
val HighContrastLightColorScheme = lightColorScheme(
    surface = HighContrastWhite,
    secondaryContainer = HighContrastBlack,
    primaryContainer = HighContrastBlack,
    onPrimaryContainer = HighContrastBlack,
    onBackground = HighContrastWhite
)

@Composable
fun PruebasTFGTheme(
    viewModel: AppViewModel,
    content: @Composable () -> Unit
) {
    val isThemeDark = viewModel.isThemeDark.collectAsState(initial = null)
    val colorTheme = viewModel.colorTheme.collectAsState(initial = "blue")
    val highContrast = viewModel.isHighContrast.collectAsState(initial = false)

    //val colors = if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
    var colors = when (colorTheme.value) {
        "blue" -> if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
        "green" -> if (isThemeDark.value == true) GreenDarkColorScheme else GreenLightColorScheme
        "purple" -> if (isThemeDark.value == true) PurpleDarkColorScheme else PurpleLightColorScheme
        else -> if (isThemeDark.value == true) BlueDarkColorScheme else BlueLightColorScheme
    }
    if (highContrast.value == true) {
        colors = when (colorTheme.value) {
            "blue" -> if (isThemeDark.value == true)
                BlueDarkColorScheme.copy(
                    secondaryContainer = BlueHighContrastSecondaryContainerDark,
                    surface = BlueHighContrastSurfaceDark,
                    onBackground = HighContrastBlack
                )
            else
                BlueLightColorScheme.copy(
                    secondaryContainer = BlueHighContrastSecondaryContainerLight,
                    surface = BlueHighContrastSurfaceLight,
                    onBackground = HighContrastWhite
                )

            "green" -> if (isThemeDark.value == true)
                GreenDarkColorScheme.copy(
                    secondaryContainer = GreenHighContrastSecondaryContainerDark,
                    surface = GreenHighContrastSurfaceDark,
                    onBackground = HighContrastBlack
                )
            else
                GreenLightColorScheme.copy(
                    secondaryContainer = GreenHighContrastSecondaryContainerLight,
                    surface = GreenHighContrastSurfaceLight,
                    onBackground = HighContrastWhite
                )

            "purple" -> if (isThemeDark.value == true)
                PurpleDarkColorScheme.copy(
                    secondaryContainer = PurpleHighContrastSecondaryContainerDark,
                    surface = PurpleHighContrastSurfaceDark,
                    onBackground = HighContrastBlack
                )
            else
                PurpleLightColorScheme.copy(
                    secondaryContainer = PurpleHighContrastSecondaryContainerLight,
                    surface = PurpleHighContrastSurfaceLight,
                    onBackground = HighContrastWhite
                )

            else -> colors // fallback
        }
    }


    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}