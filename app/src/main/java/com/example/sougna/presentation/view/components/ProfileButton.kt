package com.example.sougna.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sougna.R

@Composable
fun ProfileButton(profileImageId: Int?) {
    Box(
        modifier = Modifier
            .size(55.dp) // Circle size increased for better spacing
            .clip(CircleShape)
            .background(Color.hsl(0f, 0f, 1f)) // White background
            .clickable {
                // Handle profile click (optional)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if (profileImageId != null) {
                painterResource(id = profileImageId) // Load profile image if available
            } else {
                painterResource(id = R.drawable.default_profile) // Default image if no profile image
            },
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp) // Size of the image
                .clip(CircleShape) // Clip the image into a circle
        )
    }
}