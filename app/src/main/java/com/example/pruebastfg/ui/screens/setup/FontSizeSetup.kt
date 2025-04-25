package com.example.pruebastfg.ui.screens.setup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.navigation.NavHostController
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
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Text(
            "Ajusta el tamaño de letra",
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )

        // Sección de ajuste de tamaño
        Column {
            Text(
                "Tamaño de letra",
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
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Box(modifier = Modifier.clickable { viewModel.decreaseFontSize() }) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "Disminuir tamaño",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                }

                // Texto de muestra con tamaño actual
                Text("Aa", fontSize = fontSize)
                Card(
                    shape = CircleShape,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    // Botón para aumentar tamaño
                    Box(
                        modifier = Modifier
                            .clickable { viewModel.increaseFontSize() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowUp,
                            contentDescription = "Aumentar tamaño",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }

        // Sección de vista previa
        Column {
            Text(
                "Vista previa",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(15.dp))

            Text(
                "Lorem ipsum odor amet, consectetuer adipiscing elit. Tempus nam tincidunt curabitur eu platea quisque.",
                fontSize = fontSize, // Usa el tamaño seleccionado
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                lineHeight = 30.sp
            )
        }
    }
}
