package com.example.sougna.presentation.view

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.R
import com.example.sougna.data.model.Product
import com.example.sougna.presentation.view.components.Footer
import com.example.sougna.presentation.viewmodel.AddProductViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    navController: NavHostController,
    products: MutableList<Product>
) {
    val viewModel: AddProductViewModel = hiltViewModel()

    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productImageUri by remember { mutableStateOf<Uri?>(null) }
    var productRating by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Image Picker Launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val uri = result.data?.data
            if (uri != null) {
                productImageUri = uri
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // عنوان الشاشة
        Text(
            text = "ADD A NEW PRODUCT  ",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )

        // اسم المنتج
        TextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("productName ") },
            modifier = Modifier.fillMaxWidth()
                .shadow(6.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        // سعر المنتج
        TextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("productPrice ") },
            modifier = Modifier.fillMaxWidth()
                .shadow(6.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        // وصف المنتج
        TextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("productDescription ") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        // تقييم المنتج
        TextField(
            value = productRating,
            onValueChange = { productRating = it },
            label = { Text("productRating ") },
            modifier = Modifier.fillMaxWidth()
                .shadow(6.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White, shape = RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        // حقل الصورة
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = android.content.Intent(
                        android.content.Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    imagePickerLauncher.launch(intent)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (productImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(productImageUri),
                    contentDescription = "product's image ",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                // بديل لأيقونة إضافة الصورة: أيقونة كاميرا
                Icon(
                    painter = painterResource(id = R.drawable.tag_24px),
                    contentDescription = "select an image",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray // لون الأيقونة
                )
            }
            Text(text = " select product's image ")
        }

        // زر إضافة المنتج
        Button(
            onClick = {
                val product = Product(
                    name = productName,
                    price = productPrice.toDoubleOrNull() ?: 0.0,
                    rating = productRating.toDoubleOrNull() ?: 0.0,
                    description = productDescription,
                    imageUri = productImageUri.toString()
                )
                viewModel.addProduct(product)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B))
        ) {
            Text(text = "ADD PRODUCT", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))
        Footer()
    }
}