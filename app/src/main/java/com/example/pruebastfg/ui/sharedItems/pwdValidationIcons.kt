package com.example.pruebastfg.ui.sharedItems

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PwdCorrectIcon() {
    Icon(
        imageVector = Icons.Rounded.Done,
        contentDescription = "Correct",
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun PwdIncorrectIcon() {
    Icon(
        imageVector = Icons.Rounded.Close,
        contentDescription = "Incorrect",
        tint = MaterialTheme.colorScheme.error
    )
}