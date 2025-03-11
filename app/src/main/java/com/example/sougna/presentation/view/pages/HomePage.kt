package com.example.sougna.presentation.view.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.sougna.presentation.view.components.AddProductButton
import com.example.sougna.presentation.view.components.Footer
import com.example.sougna.presentation.view.components.HeroSection
import com.example.sougna.presentation.view.components.Navbar

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Navbar()
        AddProductButton(navController = navController)
        HeroSection()
        Footer()
    }
}