package com.example.demonewsapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PreviewTheme(content: @Composable () -> Unit) {
    DemoNewsAppTheme {
        SystemUISetup {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                content()
            }
        }
    }
}


@Composable
private fun SystemUISetup(content: @Composable () -> Unit) {
    val systemController = rememberSystemUiController()
    val isSystemInDarkMode = isSystemInDarkTheme()

    SideEffect {
        systemController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isSystemInDarkMode
        )
    }
    content()
}