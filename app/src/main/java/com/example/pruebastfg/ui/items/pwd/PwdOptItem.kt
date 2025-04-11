package com.example.pruebastfg.ui.items.pwd

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PwdOptItem(icon: ImageVector, onClick: () -> Unit){
    Card(
        shape = CircleShape,
        modifier = Modifier
            .width(150.dp)
            .height(80.dp)
            .padding(5.dp),
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.padding(10.dp))
        }
    }
}