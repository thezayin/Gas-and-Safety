package com.thezayin.domain.repository

import com.thezayin.databases.model.CartModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling order-related operations.
 *
 * This interface defines the contract for placing orders,
 * including all necessary details for order creation.
 */
interface OrderRepository {
    /**
     * Places an order with the given details.
     *
     * @param userID The unique identifier of the user placing the order.
     * @param name The name of the user.
     * @param phoneNumber The user's phone number.
     * @param address The delivery address for the order.
     * @param area The area of the delivery address.
     * @param city The city of the delivery address.
     * @param email The user's email address (optional).
     * @param message Any additional message or instructions for the order (optional).
     * @param orderDate The date the order is placed.
     * @param orderTime The time the order is placed.
     * @param orderDateTime The complete date and time of the order.
     * @param orderStatus The current status of the order.
     * @param paymentMethod The method of payment used for the order.
     * @param totalAmount The total amount for the order.
     * @param orders A list of cart items included in the order.
     * @return A Flow emitting a Resource indicating the success or failure of the operation.
     */
    fun createOrder(
        userID: String,
        name: String,
        phoneNumber: String,
        address: String,
        area: String,
        city: String,
        email: String? = null,
        message: String? = null,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<CartModel>
    ): Flow<Resource<Boolean>>
}