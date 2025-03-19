package com.example.sougna.presentation.view.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.R
import com.example.sougna.presentation.viewmodel.AddProductEvent
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import com.example.sougna.presentation.viewmodel.ProductViewModel

@Composable
fun AddProductScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel // ✅ تمرير ProductViewModel
) {
    val addProductViewModel: AddProductViewModel = hiltViewModel()
    val state by addProductViewModel.state.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        addProductViewModel.onEvent(AddProductEvent.ImageSelected(uri))
    }
    val isProductAdded by addProductViewModel.isProductAdded.collectAsState()
    val newProduct by addProductViewModel.newProduct.collectAsState()

    LaunchedEffect(isProductAdded) {
        if (isProductAdded && newProduct != null) {
            productViewModel.addProduct(newProduct!!) // ✅ تحديث القائمة
            navController.popBackStack() // ✅ العودة للخلف
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFE67E7E), RoundedCornerShape(24.dp))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Add Product", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(30.dp))

        TextFieldWithLabel(
            value = state.name,
            label = "Name of product",
            placeholder = "Enter name of product",
            labelPadding = 20.dp
        ) { addProductViewModel.onEvent(AddProductEvent.NameChanged(it)) }

        TextFieldWithLabel(
            value = state.description,
            label = "Description of Product",
            placeholder = "Enter description of product",
            minLines = 4,
            labelPadding = 20.dp
        ) { addProductViewModel.onEvent(AddProductEvent.DescriptionChanged(it)) }

        TextFieldWithLabel(
            value = state.price,
            label = "Price of Product",
            placeholder = "Enter price of product",
            labelPadding = 20.dp
        ) { addProductViewModel.onEvent(AddProductEvent.PriceChanged(it)) }

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Picture of Product",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(135.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (state.imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(state.imageUri),
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = "Upload Image"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        CategoryDropdown(
            selectedCategory = state.categoryId,
            onCategorySelected = { addProductViewModel.onEvent(AddProductEvent.CategorySelected(it)) }
        )


        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier
                    .weight(0.5f)
                    .height(45.dp)
            ) {
                Text(text = "Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { addProductViewModel.onEvent(AddProductEvent.Submit) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE67E7E)),
                modifier = Modifier
                    .weight(0.5f)
                    .height(45.dp)
            ) {
                Text(text = "Add")
            }
        }

        if (state.isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Adding product...", color = Color.Gray)
        }

        state.error?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = Color.Red)
        }
    }
}

@Composable
fun CategoryDropdown(
    selectedCategory: Int,
    onCategorySelected: (Int) -> Unit
) {
    val categories = listOf(1 to "Shoes", 2 to "Bags", 3 to "Clothes") // ✅ قائمة التصنيفات
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = categories.find { it.first == selectedCategory }?.second ?: "Select Category",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            label = { Text("Category") }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categories.forEach { (id, name) ->
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        onCategorySelected(id) // ✅ تحديث categoryId
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(
    value: String,
    label: String,
    placeholder: String = "",
    minLines: Int = 1,
    labelPadding: Dp = 0.dp,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = labelPadding)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            minLines = minLines,
            placeholder = { Text(text = placeholder, color = Color.Gray) }
        )
    }


}