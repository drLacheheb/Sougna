package com.example.sougna.view.buttons

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun BackPressToExit() {
    val context = LocalContext.current // Get current context
    var backPressedOnce by remember { mutableStateOf(false) }

    BackHandler {
        if (backPressedOnce) {
            (context as? Activity)?.finish() // Exit the app
        } else {
            backPressedOnce = true
        }
    }

    // Show toast when backPressedOnce becomes true
    if (backPressedOnce) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Click again to exit", Toast.LENGTH_SHORT).show()
            delay(2000)
            backPressedOnce = false // Reset after 2 seconds
        }
    }
}

