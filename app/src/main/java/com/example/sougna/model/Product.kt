package com.example.sougna.model

import java.util.Date

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val userId: String,
    val categoryId: String,
    val thumbnailUrl: String,
    val rating: Double,  // ✅ إضافة التقييم
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
