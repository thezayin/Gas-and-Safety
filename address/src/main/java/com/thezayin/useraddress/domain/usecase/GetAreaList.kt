package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.useraddress.domain.repository.AreaRepository
import kotlinx.coroutines.flow.Flow

interface GetAreaList : suspend (String) -> Flow<Response<List<String>>>

class GetAreaListImpl(private val repository: AreaRepository) :
    GetAreaList {
    override suspend fun invoke(city: String): Flow<Response<List<String>>> =
        repository.getAreaList(city)
}