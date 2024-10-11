package com.thezayin.data

import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.repository.CartRepository
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Implementation of [CartRepository] that interacts with the local database through [CartDao].
 * Provides functionality to manage cart operations, including adding, updating, retrieving, and deleting cart items.
 */
class CartRepositoryImpl(
    private val cartDao: CartDao
) : CartRepository {

    // Date format for adding the current date to cart items
    private val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())

    /**
     * Retrieves all products from the cart.
     * Emits a [Resource] containing either the list of [CartModel] items or an error message.
     */
    override fun getAllCartItems(): Flow<Resource<List<CartModel>>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            val cartList = cartDao.getAllProducts() // Retrieve all cart items from DAO
            emit(Resource.Success(cartList)) // Emit success with cart items
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An error occurred while retrieving cart items"
                )
            )
        }
    }

    /**
     * Adds a product to the cart. If the product already exists, it will be replaced.
     * Emits a [Resource] indicating the success or failure of the operation.
     */
    override fun addProductToCart(
        id: String,
        name: String,
        price: String,
        description: String,
        imageUri: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            val cartModel = CartModel(
                externalId = id,
                name = name,
                price = price.toDouble(),
                totalPrice = price.toDouble(),
                description = description,
                date = currentDate,
                imageUri = imageUri,
                quantity = 1
            )
            cartDao.upsertCartItem(cartModel) // Insert or update the cart item in DAO
            emit(Resource.Success(true)) // Emit success when cart is updated
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An error occurred while adding the product to the cart"
                )
            )
        }
    }

    /**
     * Updates the quantity and total price of an existing product in the cart.
     * Emits a [Resource] indicating the success or failure of the operation.
     */
    override fun updateProductQuantity(
        id: String,
        quantity: Int,
        totalPrice: Int
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            val cartModel = cartDao.getAllProducts()
                .find { it.externalId == id } // Find the cart item by ID
            cartModel?.let {
                val updatedCartModel =
                    it.copy(quantity = quantity, totalPrice = totalPrice.toDouble())
                cartDao.upsertCartItem(updatedCartModel) // Update the cart item with new quantity and price
                emit(Resource.Success(true)) // Emit success
            } ?: emit(Resource.Error("Product not found in the cart")) // Emit error if product not found
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An error occurred while updating the product in the cart"
                )
            )
        }
    }

    /**
     * Deletes a product from the cart by its unique identifier.
     * Emits a [Resource] indicating the success or failure of the operation.
     */
    override fun removeProductFromCart(id: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            cartDao.deleteProductById(id) // Remove the product from DAO by ID
            emit(Resource.Success(true)) // Emit success
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage
                        ?: "An error occurred while removing the product from the cart"
                )
            )
        }
    }

    /**
     * Clears all products from the cart.
     * Emits a [Resource] indicating the success or failure of the operation.
     */
    override fun clearCart(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            cartDao.clearCart() // Remove all products from DAO
            emit(Resource.Success(true)) // Emit success when cart is cleared
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred while clearing the cart"))
        }
    }
}