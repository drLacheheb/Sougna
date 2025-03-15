package com.example.sougna.data.model

import java.util.Date

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val userId: String = "",
    val categoryId: String = "",
    val thumbnailUrl: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val imageUri: String = ""
) {
    constructor(rating: Float) : this(
        "",
        "",
        "",
        0.0,
        rating.toDouble(),
        "",
        "",
        "",
        Date(),
        Date(),
        ""
    )
}