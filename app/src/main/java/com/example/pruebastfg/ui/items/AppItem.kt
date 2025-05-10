package com.example.pruebastfg.ui.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.ui.models.AppModel


@Composable
fun AppItem(app: AppModel, onClick: () -> Unit) {

    // Animación para el color del borde (transparent -> primary)
    val animatedBorderColor by animateColorAsState(
        targetValue = if (app.isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 200),
        label = "borderColorAnimation"
    )

    // Animación para el padding
    val animatedPadding by animateDpAsState(
        targetValue = if (app.isSelected) 25.dp else 10.dp,
        animationSpec = tween(
            durationMillis = 50, // Duración de la animación en milisegundos
            easing = FastOutSlowInEasing // Curva de aceleración/desaceleración
        ),
        label = "paddingAnimation"
    )

    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
            border = BorderStroke(3.dp, animatedBorderColor), // Borde dinámico por selecciónº
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Make the card square
                .padding(animatedPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize().clickable { onClick() }.padding(10.dp),
                contentAlignment = Alignment.BottomEnd

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        bitmap = app.icon.asImageBitmap(),
                        contentDescription = app.name,
                        modifier = Modifier.size(110.dp),
                        contentScale = ContentScale.Fit
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
                text = app.name,
                fontSize = 20.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
            )
        }
    }
}
