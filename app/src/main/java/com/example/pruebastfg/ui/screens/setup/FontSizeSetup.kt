package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.pruebastfg.R
import com.example.pruebastfg.ui.AppScreens
import com.example.pruebastfg.ui.AppViewModel


@Composable
fun FontSizeSetup(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    val fontSize by viewModel.fontSize.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(R.string.ajusta_el_tamano_de_letra),
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp).height(80.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        // Sección de ajuste de tamaño
        Column(modifier = Modifier.height(100.dp)) {
            Text(
                stringResource(R.string.tamano_de_letra),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)


            ) {
                // Botón para disminuir tamaño
                Card(
                    shape = CircleShape,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Box(modifier = Modifier.clickable { viewModel.decreaseFontSize() }) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = stringResource(R.string.disminuir_tamano),
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                }

                // Texto de muestra con tamaño actual
                Text("Aa", fontSize = fontSize)
                Card(
                    shape = CircleShape,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    // Botón para aumentar tamaño
                    Box(
                        modifier = Modifier
                            .clickable { viewModel.increaseFontSize() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowUp,
                            contentDescription = stringResource(R.string.aumentar_tamano),
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }

        // Sección de vista previa
        Column(modifier = Modifier.height(300.dp)) {
            Text(
                stringResource(R.string.vista_previa),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(15.dp))

//            Text(
//                "Lorem ipsum odor amet, consectetuer adipiscing elit. Tempus nam tincidunt curabitur eu platea quisque.",
//                fontSize = fontSize, // Usa el tamaño seleccionado
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(horizontal = 22.dp)
//                    .fillMaxWidth(),
//                fontWeight = FontWeight.SemiBold,
//                lineHeight = 30.sp
//            )
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier
                    .padding(10.dp)
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            imageVector = Icons.Rounded.Circle,
                            contentDescription = stringResource(R.string.aumentar_tamano),
                            tint = MaterialTheme.colorScheme.surfaceTint,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.easyway_ui),
                    fontSize = fontSize,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
