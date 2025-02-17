package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sougna.ui.theme.SougnaTheme
import com.example.sougna.presentation.view.AddProductScreen
import com.example.sougna.presentation.view.MainScreen
import com.example.sougna.presentation.viewmodel.CategoryViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main activity for the app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SougnaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                            MainScreen(
                                modifier = Modifier.padding(innerPadding),
                                onAddProductClick = { navController.navigate("addProduct") }
                            )
                        }
                    }
                    composable("addProduct") {
                         Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AddProductScreen(
                            modifier = Modifier.padding(innerPadding),
                            onBackClick = { navController.popBackStack() }
                        )
                         }
                    }
                }
            }
        }
    }
}
