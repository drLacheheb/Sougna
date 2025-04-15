package com.example.sougna.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sougna.R
import com.example.sougna.data.model.Product

@Composable
fun ProductGrid(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .shadow(8.dp, shape = RoundedCornerShape(12.dp)), // Added shadow
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Product Image
            Image(
                painter = rememberAsyncImagePainter(product.thumbnailUrl),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Product Name
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Price & Rating Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Product Price
                Text(
                    text = "${product.price} DA",
                    fontSize = 14.sp,
                    color = Color(0xFFE63946),
                    fontWeight = FontWeight.Bold
                )

                // Product Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = product.rating.toString(),
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            // Divider Line
            Divider(
                modifier = Modifier.padding(top = 6.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.5f)
            )

            // Contact Icons Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Phone Icon
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Phone Contact",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )

                // Chat Icon
                Icon(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = "Chat Contact",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
