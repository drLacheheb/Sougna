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
import androidx.navigation.NavController
import com.example.sougna.R

@Composable
fun ProfileButton(navController: NavController, profileImageId: Int?) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable {
                navController.navigate("profile") // Ensure "profile" is the correct route
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = profileImageId ?: R.drawable.default_profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}

