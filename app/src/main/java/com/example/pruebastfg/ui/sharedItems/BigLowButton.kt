package com.example.pruebastfg.ui.sharedItems

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BigLowButton(onButtonClick: () -> Unit, text: String, isAtras: Boolean, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center, modifier = modifier.padding(bottom = 10.dp)) {
        Card(
            shape = Shapes().large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            onClick = onButtonClick,
            modifier = Modifier
                .width(360.dp)
                .padding(10.dp)
                .padding(bottom = 10.dp)
                .border(2.dp, MaterialTheme.colorScheme.surfaceDim, shape = Shapes().large),
            //colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (isAtras) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = text,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                )
            }
        }
    }
}