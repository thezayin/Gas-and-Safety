package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProWithImg :
    suspend (List<com.thezayin.entities.HomeProductsModel>) -> Flow<Response<List<com.thezayin.entities.HomeProductsModel>>>

class GetProWithImgImpl(private val repository: com.thezayin.domain.GetProductsRepository) :
    GetProWithImg {
    override suspend fun invoke(list: List<com.thezayin.entities.HomeProductsModel>): Flow<Response<List<com.thezayin.entities.HomeProductsModel>>> =
        repository.getProductsWithImages(list)

}