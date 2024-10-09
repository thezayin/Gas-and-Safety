package com.thezayin.domain.usecase

import com.thezayin.databases.model.CartModel
import com.thezayin.domain.repository.OrderRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for creating an order.
 *
 * This interface defines the contract for placing an order
 * with the necessary details and returns a flow indicating success or failure.
 */
interface CreateOrder : suspend (
    String,
    String,
    String,
    String?,
    String,
    String,
    String,
    String?,
    String,
    String,
    String,
    String,
    String,
    String,
    List<CartModel>
) -> Flow<Resource<Boolean>>

/**
 * Implementation of the [CreateOrder] use case.
 *
 * This class interacts with the [OrderRepository] to create an order.
 *
 * @property orderRepository The repository responsible for order operations.
 */
class CreateOrderImpl(private val orderRepository: OrderRepository) : CreateOrder {

    /**
     * Executes the use case to create an order with the provided details.
     *
     * @param userID The unique identifier of the user placing the order.
     * @param name The name of the user.
     * @param phoneNumber The user's phone number.
     * @param email The user's email address (optional).
     * @param address The delivery address for the order.
     * @param area The area of the delivery address.
     * @param city The city of the delivery address.
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
    override suspend fun invoke(
        userID: String,
        name: String,
        phoneNumber: String,
        email: String?,
        address: String,
        area: String,
        city: String,
        message: String?,
        orderDate: String,
        orderTime: String,
        orderDateTime: String,
        orderStatus: String,
        paymentMethod: String,
        totalAmount: String,
        orders: List<CartModel>
    ): Flow<Resource<Boolean>> = orderRepository.createOrder(
        userID = userID,
        name = name,
        phoneNumber = phoneNumber,
        email = email,
        address = address,
        area = area,
        city = city,
        message = message,
        orderDate = orderDate,
        orderTime = orderTime,
        orderDateTime = orderDateTime,
        orderStatus = orderStatus,
        paymentMethod = paymentMethod,
        totalAmount = totalAmount,
        orders = orders
    )
}