package com.example.sougna.model

/**
 * Represents a product category in the application.
 *
 * @property id Unique identifier for the category.
 * @property name Name of the category.
 * @property description Description of the category.
 * @property icon Resource ID for the category's icon.
 */
data class Category(
    val id: String,
    val name: String,
    val description: String,
    val icon: Int
)
