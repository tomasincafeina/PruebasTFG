package com.example.pruebastfg.ui.screens.setup


import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Contrast
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pruebastfg.R
import androidx.compose.ui.draw.rotate
import com.example.pruebastfg.ui.items.ThemeColorPickerItem
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebastfg.data.models.ThemeColorPickerModel
import com.example.pruebastfg.ui.sharedItems.BigLowButton
import com.example.pruebastfg.ui.theme.BlueprimaryLight
import com.example.pruebastfg.ui.theme.GreenprimaryLight
import com.example.pruebastfg.ui.theme.PurpleprimaryLight

@Composable
fun ThemePickerSetup(
    navController: NavController,
    isThemeDark: Boolean,
    isHighContrast: Boolean,
    toogleHighContrast: () -> Unit,
    changeThemeToDark: () -> Unit,
    changeThemeToLight: () -> Unit,
    colorTheme: String?,
    setColorTheme: (String) -> Unit,
    onClickTerminar: () -> Unit

) {
    val colorPickerList = listOf(
        ThemeColorPickerModel("blue", BlueprimaryLight),
        ThemeColorPickerModel("green", GreenprimaryLight),
        ThemeColorPickerModel("purple", PurpleprimaryLight)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()

    ) {
        Text(
            stringResource(R.string.color_a_tu_gusto),
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 30.sp
        )
        //Spacer(modifier = Modifier.heightIn(min = 20.dp))
        Column {
            Text(
                stringResource(R.string.modo),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                    if (isThemeDark == true) {
                        Card(
                            shape = CircleShape,
                            colors = if (isThemeDark == true) {
                                androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                            } else {
                                androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            }
                        ) {
                            Box(modifier = Modifier.clickable { changeThemeToLight() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.outline_light_mode_20),
                                    contentDescription = "light_mode",
                                    modifier = Modifier
                                        .size(150.dp)
                                        .padding(10.dp),
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    } else {
                        Card(
                            shape = CircleShape,
                            colors = if (isThemeDark == true) {
                                androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            } else {
                                androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                            }
                        ) {
                            Box(modifier = Modifier.clickable { changeThemeToDark() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.outline_dark_mode_20),
                                    contentDescription = "dark_mode",
                                    modifier = Modifier
                                        .size(150.dp),
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (isThemeDark==true) {
                        Text("Claro")
                    }else{
                        Text("Oscuro")
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))
                HighContrastToggle(isHighContrast = isHighContrast, onToggle = { toogleHighContrast() })
            }
        }
        Column {
            Text(
                stringResource(R.string.elige_el_color),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth(),
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
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
        }
    }

}
@Composable
fun HighContrastToggle(
    isHighContrast: Boolean,
    onToggle: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isHighContrast) 180f else 0f,
        label = "Contrast Rotation"
    )

    val containerColor by animateColorAsState(
        targetValue = if (isHighContrast)
            MaterialTheme.colorScheme.secondaryContainer
        else
            MaterialTheme.colorScheme.surface,
        label = "Contrast Card Color"
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Box(modifier = Modifier
                .clickable { onToggle() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Contrast,
                    contentDescription = "High Contrast Mode",
                    modifier = Modifier
                        .size(150.dp)
                        .rotate(rotation),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Alto contraste")
    }
}




