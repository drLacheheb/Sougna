package com.example.sougna.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.R
import com.example.sougna.model.Category
import com.example.sougna.model.Product
import com.example.sougna.viewmodel.CategoryViewModel
import com.example.sougna.viewmodel.ProductViewModel

@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        categoryViewModel.loadCategories()
    }

    val categories by categoryViewModel.categories.collectAsState()
    val productState by productViewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        // ✅ إضافة صورة الحساب على اليمين
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Sougna", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = "Order your favorite Product!", fontSize = 14.sp, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = R.drawable.profile_picture), // ضع صورة الحساب هنا
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(50))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        SearchBar(
            onSearch = { query -> productViewModel.searchProducts(query) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        CategoryList(categories = categories)

        Spacer(modifier = Modifier.height(16.dp))
        when {
            productState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            productState.error != null -> Text(
                text = "Error: ${productState.error}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            productState.products.isNotEmpty() -> ProductList(products = productState.products)
            else -> Text("No products available", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            label = { Text("Search...") },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .weight(1f) // يجعل مربع البحث يأخذ معظم المساحة
                .padding(end = 8.dp) // مسافة بين مربع البحث وأيقونة الإعدادات
        )

        // ✅ أيقونة الإعدادات بجانب مربع البحث
        IconButton(
            onClick = { /* منطق الإعدادات هنا */ },
            modifier = Modifier.size(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.filtre), // استخدم صورة الإعدادات
                contentDescription = "Settings Icon"
            )
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>) {
    LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.padding(8.dp)) {
        items(categories) { category ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = category.name,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = category.name, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(8.dp)) {
        items(products) { product ->
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(product.thumbnailUrl),
                        contentDescription = product.name,
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.name, fontWeight = FontWeight.Bold)
                    Text(text = product.description, fontSize = 12.sp, color = Color.Gray, maxLines = 2)
                    Text(text = "${product.price} DA", color = Color(0xFFFFA000), fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "${product.rating} ⭐", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "📞 Appeler  ✉️ Send", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
