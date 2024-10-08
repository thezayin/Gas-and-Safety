package com.thezayin.domain.model

import com.google.firebase.Timestamp
import com.thezayin.databases.model.CartModel

/**
 * Data class representing a user's order.
 *
 * This class holds all relevant information for an order, including
 * user details, order status, and a list of cart items associated with the order.
 *
 * @property id The unique identifier for the order (optional).
 * @property userID The unique identifier of the user placing the order (optional).
 * @property name The name of the user (optional).
 * @property phoneNumber The user's phone number (optional).
 * @property address The delivery address for the order (optional).
 * @property area The area of the delivery address (optional).
 * @property city The city of the delivery address (optional).
 * @property email The user's email address (optional).
 * @property message Any additional message or instructions from the user (optional).
 * @property orderDate The date the order was placed (optional).
 * @property orderTime The time the order was placed (optional).
 * @property orderDateTime The complete date and time of the order as a Timestamp (optional).
 * @property orderStatus The current status of the order (optional).
 * @property paymentMethod The method of payment used for the order (optional).
 * @property totalAmount The total amount for the order (optional).
 * @property orders A list of cart items included in the order (optional).
 */
data class UserOrderModel(
    val id: String? = null,
    val userID: String? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val area: String? = null,
    val city: String? = null,
    val email: String? = null,
    val message: String? = null,
    val orderDate: String? = null,
    val orderTime: String? = null,
    val orderDateTime: Timestamp? = null,
    val orderStatus: String? = null,
    val paymentMethod: String? = null,
    val totalAmount: String? = null,
    val orders: List<CartModel>? = null
)