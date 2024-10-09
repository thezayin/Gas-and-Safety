package com.thezayin.domain.model

import com.google.firebase.Timestamp
import com.thezayin.databases.model.CartModel

/**
 * Data class representing an Order in the system.
 * It contains all necessary details regarding an order placed by the user, including user details,
 * order details, and cart items associated with the order.
 */
data class OrderModel(
    val id: String? = null,                  // Unique identifier for the order
    val userId: String? = null,              // ID of the user who placed the order
    val name: String? = null,                // Name of the user
    val phoneNumber: String? = null,         // Contact number of the user
    val email: String? = null,               // Email of the user
    val address: String? = null,             // Delivery address
    val area: String? = null,                // Specific area of the address
    val city: String? = null,                // City where the order will be delivered
    val message: String? = null,             // Additional message or note provided by the user
    val orderDate: String? = null,           // Date when the order was placed (formatted as a string)
    val orderTime: String? = null,           // Time when the order was placed (formatted as a string)
    val orderDateTime: Timestamp? = null,    // Firebase Timestamp representing the exact date and time of the order
    val orderStatus: String? = null,         // Current status of the order (e.g., pending, shipped, delivered)
    val paymentMethod: String? = null,       // Payment method used for the order (e.g., cash, card, online)
    val totalAmount: String? = null,         // Total amount charged for the order
    val orders: List<CartModel>? = null      // List of items (CartModel) included in the order
)