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
        columns = GridCells.Fixed(2), // Two columns
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
            .shadow(8.dp, shape = RoundedCornerShape(12.dp)), // Added shadow here
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Added elevation
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                horizontalArrangement = Arrangement.SpaceBetween // Proper spacing
            ) {
                Text(
                    text = "${product.price} DA",
                    fontSize = 14.sp,
                    color = Color(0xFFE63946),
                    fontWeight = FontWeight.Bold
                )

                Row {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow, // Changed to Gray
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

            // **Line Divider**
            Divider(
                modifier = Modifier.padding(top = 6.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.5f) // Light gray color
            )

            // Apple & Chat Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Apple Section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "Apple",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )

//                    Text(
//                        text = product.apple.toString(), // Ensure it's a String
//                        fontSize = 14.sp,
//                        color = Color.Black,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
                }

                // Chat Section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )

//                    Text(
//                        text = product.chat.toString(), // Ensure it's a String
//                        fontSize = 14.sp,
//                        color = Color.Black,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
                }
            }
        }
    }
}
