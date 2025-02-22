package com.example.sougna.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sougna.model.Category
import com.example.sougna.model.Product
import com.example.sougna.viewmodel.CategoryViewModel
import com.example.sougna.viewmodel.ProductViewModel


/**
 * Composable function that displays a list of categories
 * @param categories The list of categories to display
 * @param modifier Modifier for the layout
 */

@Composable
fun CategoryList(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    val rowHeight = 60.dp
    val gridHeight = rowHeight * 2

    val displayedCategories = categories.take(n = 10)

    Box(
        modifier = modifier
            .height(gridHeight)
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(displayedCategories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .height(60.dp), // زيادة الارتفاع
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = category.icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 9.sp,
            color = Color.Blue

        )
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 أعمدة
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp) // حشو حول الشبكة
    ) {
        items(products) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 170.dp), // تحديد ارتفاع أدنى للبطاقة
        shape = RoundedCornerShape(20.dp) // زوايا مستديرة
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Product image
            AsyncImage(
                model = product.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp) // تقليل ارتفاع الصورة
            )
            Spacer(modifier = Modifier.height(8.dp)) // تقليل المسافة
            // Product name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1, // تحديد عدد الأسطر
                overflow = TextOverflow.Ellipsis // إضافة "..." إذا تجاوز النص المساحة
            )
            Spacer(modifier = Modifier.height(4.dp)) // تقليل المسافة
            // Product description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2, // تحديد عدد الأسطر
                overflow = TextOverflow.Ellipsis // إضافة "..." إذا تجاوز النص المساحة
            )
            Spacer(modifier = Modifier.height(4.dp)) // تقليل المسافة
            // Product price
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
/**
 * Main screen composable that displays both categories and products
 * @param productViewModel ViewModel for product data
 * @param categoryViewModel ViewModel for category data
 * @param modifier Modifier for the layout
 */
@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {
    // Collect state from ViewModels
    val productState by productViewModel.uiState.collectAsState()
    val categoryState by categoryViewModel.categoryState.collectAsState()

    // State for search query
    var searchQuery by remember { mutableStateOf("") }

    // Filter products  based on search query
    val filteredProducts = productState.products.filter { product ->
        product.name.contains(searchQuery, ignoreCase = true) ||
                product.description.contains(searchQuery, ignoreCase = true)
    }


    Column(modifier = modifier.padding(16.dp)) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search products ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Display category list
        CategoryList(
            categories = categoryState.categories,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Handle product state
        when {
            productState.isLoading -> {
                // Show loading indicator
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            productState.error != null -> {
                // Show error message
                Text(
                    text = "Error: ${productState.error}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                // Display filtered product list
                ProductList(
                    products = filteredProducts,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}