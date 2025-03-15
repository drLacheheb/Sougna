package com.example.sougna.presentation.view.Navigatore

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sougna.presentation.view.AddProductScreen
import com.example.sougna.presentation.view.IntroScreen
import com.example.sougna.presentation.view.pages.HomePage
import androidx.compose.runtime.mutableStateListOf
import com.example.sougna.data.model.Product

@SuppressLint("UnrememberedMutableState")
@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val products = mutableStateListOf<Product>() // إنشاء قائمة المنتجات

    NavHost(navController, startDestination = "intro") {
        // صفحة الانترو
        composable("intro") {
            IntroScreen(navController)
        }

        // الصفحة الرئيسية
        composable("home") {
            HomePage(navController)
        }

        // صفحة إضافة المنتج
        composable("addProduct") {
            AddProductScreen(navController , products) // تمرير قائمة المنتجات
        }
    }
}