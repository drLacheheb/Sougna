package com.example.sougna.presentation.view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sougna.R
import com.example.sougna.presentation.viewmodel.CategoryViewModel

@Composable
fun Navbar(navController: NavController) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categoriesState by categoryViewModel.categoryState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()) // Enables scrolling
            .animateContentSize() // Smooth transition effect
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Sougna",
                    fontSize = 36.sp,
                    fontWeight = FontWeight(900),
                    fontFamily = FontFamily.Cursive
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "Order your favorite product",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Cursive
                )
            }

            ProfileButton(profileImageId = R.drawable.damim_x_adi, navController = navController)
        }

        Spacer(modifier = Modifier.height(20.dp))

        SearchBar()

        Spacer(modifier = Modifier.height(5.dp))

        CategoriesRow(categoriesState.categories)
    }
}