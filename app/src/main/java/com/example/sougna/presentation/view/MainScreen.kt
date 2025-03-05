package com.example.sougna.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.sougna.presentation.view.buttons.BackPressToExit
import com.example.sougna.presentation.viewmodel.CategoryViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel



/**
 * Composable function that displays a list of categories
 * @param categories The list of categories to display
 * @param modifier Modifier for the layout
 */
@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {
    BackPressToExit()

    Column(modifier = modifier.fillMaxSize()) {
        Text("Welcome to the app!")
    }
//    val products by productViewModel.products.collectAsState()
//    val categories by categoryViewModel.categories.collectAsState()
}

