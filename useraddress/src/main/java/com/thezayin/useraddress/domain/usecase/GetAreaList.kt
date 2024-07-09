package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetAreaList : suspend (String) -> Flow<Response<List<String>>>

class GetAreaListImpl(private val repository: com.thezayin.useraddress.domain.repository.AreaRepository) :
    GetAreaList {
    override suspend fun invoke(city: String): Flow<Response<List<String>>> =
        repository.getAreaList(city)
}