package com.example.sougna.presentation.view.Navigatore

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sougna.presentation.view.pages.HomePage
import com.example.sougna.presentation.view.pages.AddProductPage
import com.example.sougna.presentation.view.IntroScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "intro") {
        composable("intro") { IntroScreen(navController) }
        composable("home") { HomePage(navController) }
        composable("addProduct") { AddProductPage(navController) } // Passing the navController here
    }
}