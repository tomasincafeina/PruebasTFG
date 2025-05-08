package com.example.pruebastfg.ui.items

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pruebastfg.AppInfo


@Composable
fun AppItemProto(
    appInfo: AppInfo,
    bitmap: Bitmap?,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    fontSize: TextUnit
) {
//    val bitmap = remember(appInfo.icon) {
//        appInfo.icon?.toBitmap()
//    }

    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Make the card square
                .padding(10.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = if (appInfo.isFavorite == true) {
                    Color(android.graphics.Color.parseColor("#f7d168"))
                } else MaterialTheme.colorScheme.secondaryContainer
            )

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
                    .padding(10.dp)
                    .let {
                        if (isSelected) {
                            it.border(2.dp, MaterialTheme.colorScheme.primary)
                        } else {
                            it
                        }
                    },
                contentAlignment = Alignment.BottomEnd

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = appInfo.name,
                        modifier = Modifier.size(110.dp),
                        contentScale = ContentScale.Fit
                    )

                }
                // Espacio reservado para el ícono de la estrella
//                Box(
//                    modifier = Modifier.size(30.dp), // Tamaño fijo para el ícono
//                    contentAlignment = Alignment.Center
//                ) {
//                    if (appInfo.isFavorite == true) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = "Estrella",
//                            modifier = Modifier.size(30.dp),
//                            tint = Color(android.graphics.Color.parseColor("#f5cb42")),
//                        )
//                    }
//                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,
                text = appInfo.name,
                fontSize = fontSize?:20.sp,
                maxLines = 2,
                lineHeight = 35.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}