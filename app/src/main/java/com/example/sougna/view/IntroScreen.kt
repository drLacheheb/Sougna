package com.example.sougna.view

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
import com.example.sougna.view.buttons.EnterButton
import com.example.sougna.view.buttons.FirstPaginition
import com.example.sougna.view.pages.IntroPage
import com.example.sougna.view.buttons.NextButton
import kotlinx.coroutines.delay
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun IntroScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
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

        HorizontalPager(count = 3, state = pagerState) { page ->
            when (page) {
                0 -> FirstPaginition("Sougna", "")
                1 -> IntroPage("Fast & Secure", "Payment and Explore is secure.")
                2 -> IntroPage("Get Started", "Let's dive into the app!")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it })
        ) {
            Row {
                if (pagerState.currentPage < 2) {
                    NextButton( onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } } )
                } else {
                    EnterButton(navController)
                }
            }
        }
    }
}

