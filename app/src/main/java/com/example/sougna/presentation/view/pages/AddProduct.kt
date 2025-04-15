package com.example.sougna.presentation.view.pages

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sougna.presentation.view.TextFeild.Product.*
import com.example.sougna.presentation.view.buttons.ProductButton.*
import com.example.sougna.presentation.view.buttons.ProfileButton.ProfileButton
import com.example.sougna.presentation.view.components.CategoryDropdown
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductViewModel

@SuppressLint("UseKtx")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AddProductScreen(
    navController: NavHostController,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onEvent(AddProductEvent.ImageUrlChanged(it.toString())) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 🔴 Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFFE57373), RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // ✅ Reduced spacing to fit all buttons
                horizontalArrangement = Arrangement.SpaceEvenly, // ✅ Ensures buttons are evenly spaced
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileButton(navController)
                AddProductButton(navController)
                //UserProductAdded(navController) // ✅ This should now appear correctly
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        // 🔴 Form Inputs Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            NameField(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(12.dp))

            DescriptionField(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(12.dp))

            PriceField(
                modifier = Modifier.fillMaxWidth(),
                productPrice = state.price.toString(),
                onPriceChange = { viewModel.onEvent(AddProductEvent.PriceChanged(it)) },
                selectedCurrency = "USD",
                onCurrencyChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            CategoryDropdown(
                selectedCategory = state.categoryId,
                onCategorySelected = { categoryId ->
                    viewModel.onEvent(AddProductEvent.CategorySelected(categoryId))
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔴 Image Picker
            ImagePicker(
                selectedImages = listOfNotNull(state.imageUrl?.let { Uri.parse(it) }),
                onImageClick = { imagePickerLauncher.launch("image/*") }
            )

            Spacer(modifier = Modifier.height(35.dp))

            // 🔴 Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CancelButton(navController)
                Spacer(modifier = Modifier.width(16.dp))
                AddButton(
                    onClick = {
                        viewModel.onEvent(AddProductEvent.Submit)
                    }
                )
            }

            // 🔴 Loading & Error State
            if (state.isLoading) {
                Text(
                    text = "Adding product...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.Gray
                )
            }

            state.error?.let {
                Text(
                    text = "Error: $it",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            // 🔴 Navigate on Success
            if (state.isSuccess) {
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        popUpTo("add_product") { inclusive = true }
                    }
                }
            }
        }
    }
}
