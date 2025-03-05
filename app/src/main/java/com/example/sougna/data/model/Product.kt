package com.example.sougna.data.model

import java.util.Date

/**
 * Represents a product in the application.
 *
 * @property id Unique identifier for the product.
 * @property name Name of the product.
 * @property description Description of the product.
 * @property price Price of the product.
 * @property userId ID of the user who created the product.
 * @property categoryId ID of the product's category.
 * @property thumbnailUrl URL for the product's thumbnail image.
 * @property createdAt Timestamp when the product was created.
 * @property updatedAt Timestamp when the product was last updated.
 */
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
    val updatedAt: Date = Date()
) {
    constructor(rating: Float) : this("", "", "", 0.0, 0.0,"", "", "", Date(), Date())
}
