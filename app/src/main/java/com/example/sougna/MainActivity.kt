package com.example.sougna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sougna.ui.theme.SougnaTheme
import com.example.sougna.view.MainScreen
import com.example.sougna.viewmodel.CategoryViewModel
import com.example.sougna.viewmodel.ProductViewModel


/**
 * Main activity for the app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SougnaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val productViewModel = viewModel<ProductViewModel>()
                    val categoryViewModel = viewModel<CategoryViewModel>()


                    MainScreen(
                        productViewModel,
                        categoryViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}


