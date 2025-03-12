package com.example.pruebastfg.ui.data.items

import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.AppInfo


@Composable
fun AppItemProto(
    appInfo: AppInfo, bitmap: Bitmap?, onClick: () -> Unit
) {
//    val bitmap = remember(appInfo.icon) {
//        appInfo.icon?.toBitmap()
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Make the card square
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = appInfo.name,
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .run {
                        if (appInfo.isFavorite == true) {
                            padding(horizontal = 10.dp)
                        } else {
                            this
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(32.dp))


                Text(
                    modifier = Modifier.weight(1f),
                    text = appInfo.name,
                    fontSize = 20.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
                // Espacio reservado para el ícono de la estrella
                Box(
                    modifier = Modifier.size(30.dp), // Tamaño fijo para el ícono
                    contentAlignment = Alignment.Center
                ) {
                    if (appInfo.isFavorite == true) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrella",
                            modifier = Modifier.size(30.dp),
                            tint = Color(android.graphics.Color.parseColor("#f5cb42")),
                        )
                    }
                }
            }
        }
    }
}