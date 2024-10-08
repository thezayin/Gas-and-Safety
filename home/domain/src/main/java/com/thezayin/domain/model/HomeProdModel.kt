package com.thezayin.domain.model

import android.net.Uri

/**
 * Data class representing a product model in the home screen.
 *
 * This model is designed to hold product information fetched from Firestore.
 * Each instance of this class represents a single product and contains various
 * attributes related to the product such as its ID, name, description, price,
 * image URL, and the date it was added.
 *
 * @property id The unique identifier for the product. This is typically fetched from Firestore.
 * @property name The name of the product.
 * @property description A brief description of the product, providing details to users.
 * @property price The price of the product, represented as a String to allow formatting.
 * @property imageUrl The URL of the product image, which can be used for displaying the product.
 * @property imageUri A Uri object representing the image of the product, useful for local storage access.
 * @property date The date when the product was added, which can be used for sorting or display purposes.
 */
data class HomeProdModel(
    val id: String? = null,              // Unique identifier for the product
    val name: String? = null,            // Name of the product
    val description: String? = null,     // Description of the product
    val price: String? = null,           // Price of the product
    val imageUrl: String? = null,        // URL for the product image
    val imageUri: Uri? = null,           // URI for the product image
    val date: String? = null              // Date when the product was added
)
