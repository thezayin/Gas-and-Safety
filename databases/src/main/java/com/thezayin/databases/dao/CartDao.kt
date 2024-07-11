package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.CartModel

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    fun getAllProduct(): List<CartModel>

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllProduct()

    @Query("DELETE FROM cart_table")
    suspend fun deleteProduct()

    @Query("DELETE FROM cart_table WHERE id = :id")
    suspend fun deleteProduct(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCart(images: CartModel)

}