package com.example.sougna.presentation.view.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sougna.R

@Composable
fun Navbar(
    onSearch: (String) -> Unit // استقبال دالة البحث كمعامل
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()) // تمكين التمرير
            .animateContentSize() // تأثير انتقال سلس
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Sougna",
                    fontSize = 36.sp,
                    fontWeight = FontWeight(900),
                    fontFamily = FontFamily.Cursive,
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "Order your favorite product",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold)

            }

            ProfileButton(profileImageId = R.drawable.profile)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // تمرير دالة البحث إلى SearchBar
        SearchBar(onSearch = onSearch)
    }
}