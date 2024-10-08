package com.thezayin.domain.usecase

import com.thezayin.domain.model.HomeProdModel
import com.thezayin.domain.repository.GetProductsRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProWithImg :
    suspend (List<HomeProdModel>) -> Flow<Response<List<HomeProdModel>>>

class GetProWithImgImpl(private val repository: GetProductsRepository) :
    GetProWithImg {
    override suspend fun invoke(list: List<HomeProdModel>): Flow<Response<List<HomeProdModel>>> =
        repository.getProductsWithImages(list)

}