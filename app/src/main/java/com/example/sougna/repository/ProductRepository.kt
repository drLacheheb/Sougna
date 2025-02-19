package com.example.sougna.repository

import com.example.sougna.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

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
                name = "Samsung S23",
                description = "هاتف مميز من Samsung بمعالج قوي.",
                price = 38000.0,
                userId = "user2",
                categoryId = "6", // الهواتف
                thumbnailUrl = "https://images.unsplash.com/photo-1709744722656-9b850470293f?w=500&auto=format&fit=crop&q=60",
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
            // ✅ منتج جديد: PlayStation 5
            Product(
                id = "4",
                name = "PlayStation 5",
                description = "أفضل منصة ألعاب تدعم تشغيل 4K.",
                price = 4999.0,
                userId = "user4",
                categoryId = "7", // الإلكترونيات
                thumbnailUrl = "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8UGxheVN0YXRpb24lMjA1fGVufDB8fDB8fHww",
                rating = 4.9,
                createdAt = Date(),
                updatedAt = Date()
            ),
            // ✅ منتج جديد: Nike Air Max
            Product(
                id = "5",
                name = "Nike Air Max",
                description = "حذاء رياضي مريح وخفيف الوزن.",
                price = 1200.0,
                userId = "user5",
                categoryId = "5", // الملابس
                thumbnailUrl = "https://images.unsplash.com/photo-1562613521-6b5293e5b0ea?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fE5pa2UlMjBBaXIlMjBNYXh8ZW58MHx8MHx8fDA%3D",
                rating = 4.6,
                createdAt = Date(),
                updatedAt = Date()
            ),
            // ✅ منتج جديد: Luxury Apartment
            Product(
                id = "6",
                name = "Luxury Apartment",
                description = "شقة فاخرة في وسط المدينة بإطلالة رائعة.",
                price = 150000.0,
                userId = "user6",
                categoryId = "4", // العقارات
                thumbnailUrl = "https://images.unsplash.com/photo-1580041065738-e72023775cdc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8THV4dXJ5JTIwQXBhcnRtZW50fGVufDB8fDB8fHww",
                rating = 4.8,
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}
