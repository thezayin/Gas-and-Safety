package com.thezayin.userhome.domain.usecase

import com.thezayin.entities.HomeProductsModel
import com.thezayin.framework.utils.Response
import com.thezayin.userhome.domain.repository.GetProductsRepository
import kotlinx.coroutines.flow.Flow

interface GetProWithImg :
    suspend (List<HomeProductsModel>) -> Flow<Response<List<HomeProductsModel>>>

class GetProWithImgImpl(private val repository: GetProductsRepository) :
    GetProWithImg {
    override suspend fun invoke(list: List<HomeProductsModel>): Flow<Response<List<HomeProductsModel>>> =
        repository.getProductsWithImages(list)

}