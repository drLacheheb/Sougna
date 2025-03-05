package com.example.sougna.presentation.view

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sougna.presentation.view.buttons.EnterButton
import com.example.sougna.presentation.view.buttons.FirstPaginition
import com.example.sougna.presentation.view.pages.IntroPage
import com.example.sougna.presentation.view.buttons.NextButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun IntroScreen(navController: NavHostController) {
    var pagerState by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    var showButton by remember { mutableStateOf(false) }
    var moveTextUp by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        showButton = true
        delay(100)
        moveTextUp = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF6B6B), Color(0xFFE63946))
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //HorizontalPager(count = 3, state = pagerState) { page ->
            when (pagerState) {
                0 -> FirstPaginition("Sougna", "")
                1 -> IntroPage("Fast & Secure", "Payment and Explore is secure.")
                2 -> IntroPage("Get Started", "Let's dive into the app!")
            }
        //}

        Spacer(modifier = Modifier.height(16.dp))


        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it })
        ) {
            Row {
                if (pagerState < 2) {
                    NextButton( onClick = { scope.launch { pagerState++ } } )
                } else {
                    EnterButton(navController)
                }
            }
        }
    }
}

