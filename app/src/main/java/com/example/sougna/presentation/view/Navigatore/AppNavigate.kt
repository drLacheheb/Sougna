package com.example.sougna.presentation.view.Navigatore

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sougna.presentation.view.IntroScreen
import com.example.sougna.presentation.view.pages.AddProductScreen
import com.example.sougna.presentation.view.pages.HomePage
import com.example.sougna.presentation.viewmodel.ProductViewModel

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    val productViewModel: ProductViewModel = hiltViewModel()

    NavHost(navController, startDestination = "intro") {
        composable("intro") { IntroScreen(navController) }
        composable("home") {
            HomePage(navController, productViewModel) // ✅ تمرير ProductViewModel
        }
        composable("addproduct") {
            AddProductScreen(navController, productViewModel) // ✅ تمرير ProductViewModel
        }
    }
}
