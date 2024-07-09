package com.thezayin.useraddress.domain.usecase

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCityList : suspend () -> Flow<Response<List<String>>>
class GetCityListImpl(private val repository: com.thezayin.useraddress.domain.repository.AreaRepository) :
    GetCityList {
    override suspend fun invoke(): Flow<Response<List<String>>> = repository.getCityList()

}