package com.example.sougna.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sougna.R
import com.example.sougna.model.Category
import com.example.sougna.model.Product
import com.example.sougna.viewmodel.CategoryViewModel
import com.example.sougna.viewmodel.ProductViewModel

/**
 * Composable function to display categories horizontally
 */
@Composable
fun CategoryList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .padding(4.dp)
                )
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

/**
 * Composable function to display products in a grid format
 */
@Composable
fun ProductGrid(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // ✅ جعل المنتجات في شبكة مكونة من عمودين
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // ✅ صورة المنتج
                    AsyncImage(
                        model = product.thumbnailUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // ✅ اسم المنتج
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // ✅ السعر والتقييم
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${product.price} DA",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFFF9800)
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // ✅ أزرار التفاعل
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TextButton(onClick = { /* Call action */ }) {
                            Text(text = "📞 Appeler")
                        }
                        TextButton(onClick = { /* Send action */ }) {
                            Text(text = "📩 Send")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {
    val productState by productViewModel.uiState.collectAsState()
    val categoryState by categoryViewModel.categoryState.collectAsState()

    var searchQuery by remember { mutableStateOf("") } // ✅ تخزين النص المكتوب في شريط البحث

    val filteredProducts = productState.products.filter { product ->
        product.name.contains(searchQuery, ignoreCase = true) // ✅ تصفية المنتجات حسب البحث
    }

    Column(modifier = modifier.padding(16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Sougna",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Order your favorite Product!",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }

            Image(
                painter = painterResource(id = R.drawable.radja),
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ شريط البحث مع البحث الديناميكي
            OutlinedTextField(
                value = searchQuery, // ✅ ربط النص المدخل بـ searchQuery
                onValueChange = { searchQuery = it }, // ✅ تحديث قيمة البحث عند الكتابة
                placeholder = { Text(text = "Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .weight(0.90f)
                    .height(50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(width = 50.dp, height = 47.dp)
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(25.dp))
                    .clickable { /* Handle settings click */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Icon",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categoryState.categories) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.size(80.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White, shape = CircleShape)
                            .border(1.dp, Color.LightGray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.name,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = category.name,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            productState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            productState.error != null -> {
                Text(
                    text = "Error: ${productState.error}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                // ✅ عرض المنتجات المفلترة فقط
                ProductGrid(
                    products = filteredProducts,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

