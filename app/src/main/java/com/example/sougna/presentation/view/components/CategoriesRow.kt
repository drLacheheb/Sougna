package com.example.sougna.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sougna.data.model.Category



@Composable
fun CategoriesRow(categories: List<Category>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(1.dp)
    ) {

        items(categories) { category ->
            CategoryCard(category)
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(6.dp) // Slightly increased padding
    ) {
        Box(
            modifier = Modifier
                .size(55.dp) // Circle size increased for better spacing
                .clip(CircleShape)
                .background(Color.hsl(220f, 0.4f, 0.95f))
                .clickable {
                    // Handle category click
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = category.name,
                modifier = Modifier
                    .size(40.dp) // Increased icon size for better visibility
            )
        }

        Spacer(modifier = Modifier.height(5.dp)) // Space between image and text

        Text(
            text = category.name,
            fontSize = 9.sp, // Slightly reduced for balance
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
