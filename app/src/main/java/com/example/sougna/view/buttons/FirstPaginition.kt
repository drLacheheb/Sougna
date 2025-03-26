package com.example.sougna.view.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstPaginition(title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sougna",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFECECEC),
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = description, fontSize = 18.sp, color = Color.White)
    }
}