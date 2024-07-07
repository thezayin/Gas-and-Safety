package com.thezayin.lpg.presentation.admin.addProducts.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.addProducts.domain.repository.AddProductRepository
import kotlinx.coroutines.flow.Flow

interface AddProductUseCase :
    suspend (String, String, String, String, String) -> Flow<Response<Boolean>>

class AddProductUseCaseImpl(private val repository: AddProductRepository) : AddProductUseCase {
    override suspend fun invoke(
        name: String,
        description: String,
        price: String,
        imageUri: String,
        date: String,
    ): Flow<Response<Boolean>> =
        repository.addProduct(
            name = name,
            description = description,
            price = price,
            imageUrl = imageUri,
            date = date,
        )
}