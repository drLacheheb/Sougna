package com.example.sougna.view

//import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
    val productState by productViewModel.uiState.collectAsState()
    val categoryState by categoryViewModel.categoryState.collectAsState()
    var searchText by remember { mutableStateOf("") }

    val filteredProducts = productState.products.filter { product ->
        product.name.contains(searchText, ignoreCase = true)
    }

    val filteredCategories = categoryState.categories.filter { category ->
        category.name.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar()
        SearchBar(
            searchText = searchText,
            onSearchTextChange = { searchText = it }
        )
        CategoryGrid(categories = filteredCategories)
        Spacer(modifier = Modifier.height(4.dp))
        ProductGrid(products = filteredProducts)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),  // Reduced padding
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Sougna", fontSize = 24.sp, fontWeight = FontWeight.Bold)  // Reduced font size
            Text("Order your favorite product!", fontSize = 15.sp)  // Reduced font size
        }
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        placeholder = { Text("Search...", fontSize = 15.sp) },  // Reduced font size
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)  // Reduced padding
            .clip(RoundedCornerShape(12.dp)),  // Reduced corner radius
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray.copy(alpha = 0.1f),
            focusedContainerColor = Color.LightGray.copy(alpha = 0.1f),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),  // Reduced corner radius
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)  // Reduced icon size
            )
        }
    )
}

@Composable
fun CategoryGrid(categories: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(6.dp)  // Reduced padding
    ) {
        items(categories) { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)  // Reduced padding
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)  // Reduced box size
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = category.icon,
                        contentDescription = category.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)  // Reduced image size
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = category.name,
                    fontSize = 12.sp,  // Reduced font size
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)  // Reduced padding
                )
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)  // Reduced padding
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier
                    .padding(4.dp)  // Reduced padding
                    .height(230.dp),  // Reduced card height
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Reduced elevation
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),  // Reduced padding
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = product.thumbnailUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)  // Reduced image height
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))  // Reduced corner radius
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)  // Reduced spacing
                    ) {
                        Text(
                            text = product.name,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp  // Reduced font size
                        )
                        Text(
                            text = "${product.price} DA",
                            color = Color(0xFFFF9800),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp  // Reduced font size
                        )
                    }
                }
            }
        }
    }
}