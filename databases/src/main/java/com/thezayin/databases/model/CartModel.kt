package com.thezayin.databases.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Represents an item in the shopping cart.
 *
 * This data class is used by Room to store and retrieve cart items from the local database.
 * It also supports serialization for network operations.
 *
 * @property localId The unique identifier for the cart item in the local Room database. It is auto-generated.
 * @property externalId The unique identifier for the cart item in an external system (e.g., Firestore). Optional.
 * @property name The name of the product.
 * @property description A brief description of the product.
 * @property price The price of a single unit of the product.
 * @property totalPrice The total price for the quantity of the product in the cart. Defaults to 0.0.
 * @property imageUri The URI of the product's image. Optional.
 * @property quantity The number of units of the product in the cart. Defaults to 1.
 * @property date The date when the product was added to the cart. Optional. Can be represented as a timestamp or formatted string.
 */
@Entity(tableName = "carts_table")
@Serializable
data class CartModel(
    @PrimaryKey(autoGenerate = true)
    val localId: Int? = null, // Renamed from _id for clarity and consistency

    /**
     * External identifier for the cart item, such as a Firestore document ID.
     * This allows mapping between the local database entry and the remote data source.
     */
    val externalId: String? = null, // Renamed from 'id' to 'externalId' for clarity

    /**
     * The name of the product.
     * Cannot be null to ensure that every cart item has a valid name.
     */
    val name: String,

    /**
     * A brief description of the product.
     * Helps users understand what the product is about.
     */
    val description: String,

    /**
     * The price of a single unit of the product.
     * Represented as a Double for accurate calculations.
     */
    val price: Double,

    /**
     * The total price for the quantity of the product in the cart.
     * Automatically calculated as price * quantity, but stored for quick access.
     */
    val totalPrice: Double = 0.0, // Default value set to 0.0

    /**
     * The URI of the product's image.
     * Optional, allowing flexibility if an image isn't available.
     */
    val imageUri: String? = null,

    /**
     * The number of units of the product in the cart.
     * Defaults to 1 to represent a single item.
     */
    val quantity: Int = 1, // Default value set to 1

    /**
     * The date when the product was added to the cart.
     * Optional, allowing the field to be unset if not needed.
     * Can be represented as a formatted string or a timestamp.
     */
    val date: String? = null
)