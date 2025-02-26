package com.example.sougna.repository

import android.provider.ContactsContract.CommonDataKinds.Im
import com.example.sougna.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import androidx.compose.foundation.Image
import coil.decode.ImageSource

class ProductRepository {

    // دالة لجلب المنتجات كـ Flow
    fun getProducts(): Flow<List<Product>> = flow {
        emit(generateMockProducts()) // إرسال قائمة المنتجات التجريبية
    }

    // قائمة المنتجات التجريبية (Mock Data)
    private fun generateMockProducts(): List<Product> {
        return listOf(
            Product(
                id = "1",
                name = "Iphone 15",
                description = "أحدث هاتف من Apple مع أداء مذهل.",
                price = 41000.0,
                userId = "user1",
                categoryId = "6", // الهواتف
                thumbnailUrl = "https://images.unsplash.com/photo-1695048132832-b41495f12eb4?w=500&auto=format&fit=crop&q=60",
                rating = 4.9,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Product(
                id = "2",
                name = "Samsung a15",
                description = "هاتف مميز من Samsung بمعالج قوي.",
                price = 38000.0,
                userId = "user2",
                categoryId = "6", // الهواتف
                thumbnailUrl = "https://images.unsplash.com/photo-1591122947157-26bad3a117d2?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                rating = 4.8,
                createdAt = Date(),
                updatedAt = Date()
            ),
            Product(
                id = "3",
                name = "MacBook Pro M2",
                description = "لابتوب قوي بشريحة M2 من Apple.",
                price = 20000.0,
                userId = "user3",
                categoryId = "3", // الحواسيب
                thumbnailUrl = "https://images.unsplash.com/photo-1580522154071-c6ca47a859ad?w=500&auto=format&fit=crop&q=60",
                rating = 4.7,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "4",
                name = "PlayStation 5",
                description = "أفضل منصة ألعاب تدعم تشغيل 4K.",
                price = 4999.0,
                userId = "user4",
                categoryId = "7",
                thumbnailUrl = "https://images.unsplash.com/photo-1607853202273-797f1c22a38e?q=80&w=1527&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                rating = 4.9,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "5",
                name = "UG حذاء",
                description = "حذاء رياضي مريح وخفيف الوزن.",
                price = 1200.0,
                userId = "user5",
                categoryId = "5",
                thumbnailUrl = "https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8U3BvcnRzJTIwc2hvZXN8ZW58MHx8MHx8fDA%3D",
                rating = 4.6,
                createdAt = Date(),
                updatedAt = Date()
            ),

            Product(
                id = "6",
                name = "شقة فاخرة",
                description = "شقة فاخرة في وسط المدينة بإطلالة رائعة.",
                price = 150000.0,
                userId = "user6",
                categoryId = "4", // العقارات
                thumbnailUrl = "https://images.unsplash.com/photo-1507149833265-60c372daea22?q=80&w=1476&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                rating = 4.8,
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}
