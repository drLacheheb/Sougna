package com.example.sougna.view.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.sougna.view.components.Footer
import com.example.sougna.view.components.HeroSection
import com.example.sougna.view.components.Navbar

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)), // Dark mode background
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Navbar()
        HeroSection()
        Footer()
    }
}