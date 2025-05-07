package com.example.pruebastfg.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebastfg.ui.sharedItems.BigLowButton

@Composable
fun SetupBottomAppBar(
    currentScreen: SetupSubScreens,
    navController: NavHostController,
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón "Atrás"
                if (navController.previousBackStackEntry != null) {
//
                    ElevatedCard(
                        shape = Shapes().large,
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        onClick = { onBack() },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 10.dp, end = 5.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Atrás",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Atrás",
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                            )
                        }
                    }
                    // Espacio vacío si no hay botón "Atrás"
                    Spacer(modifier = Modifier.width(8.dp))
                }
                // Botón "Siguiente"
                ElevatedCard(
                    shape = Shapes().large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    onClick = { onNext() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
//                    Box(
//                        modifier = Modifier
//                            .padding(20.dp)
//                            .fillMaxSize(),
//                       contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "Siguiente",
//                            textAlign = TextAlign.Center,
//                            fontSize = 25.sp,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                    ) {
                        Text(
                            text = "Siguiente",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SetupBottomAppBarPreview() {
    SetupBottomAppBar(
        currentScreen = SetupSubScreens.username,
        navController = NavHostController(androidx.compose.ui.platform.LocalContext.current),
        onNext = { /*TODO*/ },
        onBack = { /*TODO*/ }
    )
}

