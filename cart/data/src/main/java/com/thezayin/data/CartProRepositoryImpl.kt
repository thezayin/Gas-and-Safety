package com.thezayin.data

import com.thezayin.databases.dao.CartDao
import com.thezayin.databases.model.CartModel
import com.thezayin.domain.repository.CartProRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date

class CartProRepositoryImpl(
    private val cartDao: CartDao
) : CartProRepository {

    val sdf = SimpleDateFormat("dd/M/yyyy")
    private val currentDate: String = sdf.format(Date())

    override fun getAllProduct(): Flow<Response<List<CartModel>>> = flow {
        try {
            emit(Response.Loading)
            val cartList = cartDao.getAllProduct()
            emit(Response.Success(cartList))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun addToCart(
        id: String, name: String, price: String, description: String, imageUri: String
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val cartModel = CartModel(
                id = id,
                name = name,
                price = price,
                totalPrice = price,
                description = description,
                date = currentDate,
                imageUri = imageUri,
                quantity = 1
            )
            cartDao.updateCart(cartModel)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun updateQuantity(
        id: String, quantity: Int, totalPrice: Int
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val cartModel = cartDao.getAllProduct().find { it.id == id }
            cartModel?.let {
                cartModel.quantity = quantity
                cartModel.totalPrice = totalPrice.toString()
                cartDao.updateCart(cartModel)
                emit(Response.Success(true))
            } ?: emit(Response.Error("Product not found"))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteFromCart(id: String): Flow<Response<Boolean>> {
        return flow {
            try {
                emit(Response.Loading)
                cartDao.deleteProduct(id)
                emit(Response.Success(true))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
    }

    override fun deleteAllFromCart(): Flow<Response<Boolean>> {
        return flow {
            try {
                emit(Response.Loading)
                cartDao.deleteAllProduct()
                emit(Response.Success(true))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
    }
}