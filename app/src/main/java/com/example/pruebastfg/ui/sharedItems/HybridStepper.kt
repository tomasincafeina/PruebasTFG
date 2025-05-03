package com.example.pruebastfg.ui.sharedItems

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HybridStepper(
    currentStep: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
) {
    val steps = listOf("Bienvenida", "Nombre", "Tema","Fuente", "Launcher", "Modo", "Contraseña", "Aplicaciones")
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, step ->
            // Step indicator and label
            StepItem(
                index= index,
                label = step,
                isActive = index == currentStep,
                isCompleted = index < currentStep,
                isFirst = index == 0,
                isLast = index == steps.lastIndex,
                activeColor = activeColor,
                inactiveColor = inactiveColor
            )

            // Add connector except for last step
            if (index != steps.lastIndex) {
                ConnectorLine(
                    isActive = index < currentStep,
                    activeColor = activeColor,
                    inactiveColor = inactiveColor
                )
            }
        }
    }
}

@Composable
private fun StepItem(
    index: Int,
    label: String,
    isActive: Boolean,
    isCompleted: Boolean,
    isFirst: Boolean,
    isLast: Boolean,
    activeColor: Color,
    inactiveColor: Color,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Circle indicator
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 2.dp,
                    color = if (isActive || isCompleted) activeColor else inactiveColor,
                    shape = CircleShape
                )
                .background(
                    color = if (isCompleted) activeColor else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
            } else {
                Text(
                    text = "${index + 1}",
                    color = if (isActive) activeColor else inactiveColor,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        // Label text
        Text(
            text = label,
            color = if (isActive) activeColor else inactiveColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun ConnectorLine(
    isActive: Boolean,
    activeColor: Color,
    inactiveColor: Color
) {
    Divider(
        modifier = Modifier
            .height(2.dp),
        color = if (isActive) activeColor else inactiveColor,
        thickness = 2.dp
    )
}