package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.model.CartModel

/**
 * Data Access Object (DAO) for managing cart-related database operations.
 * Provides methods to perform CRUD (Create, Read, Update, Delete) operations on the cart items.
 */
@Dao
interface CartDao {

    /**
     * Retrieves all products from the carts table.
     *
     * @return A list of [CartModel] representing all products in the cart.
     */
    @Query("SELECT * FROM carts_table")
    suspend fun getAllProducts(): List<CartModel>

    /**
     * Deletes all products from the carts table.
     *
     * This method removes every entry in the cart, effectively clearing it.
     */
    @Query("DELETE FROM carts_table")
    suspend fun clearCart()

    /**
     * Deletes a specific product from the carts table based on its unique ID.
     *
     * @param id The unique identifier of the product to be deleted.
     */
    @Query("DELETE FROM carts_table WHERE localId = :id")
    suspend fun deleteProductById(id: String)

    /**
     * Inserts a new product into the carts table.
     *
     * If a product with the same primary key already exists, it will be replaced.
     *
     * @param cartItem The [CartModel] representing the product to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCartItem(cartItem: CartModel)
}
