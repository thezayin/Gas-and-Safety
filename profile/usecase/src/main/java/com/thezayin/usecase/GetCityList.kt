package com.thezayin.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.AreaRepository
import kotlinx.coroutines.flow.Flow

interface GetCityList : suspend () -> Flow<Response<List<String>>>
class GetCityListImpl(private val repository: AreaRepository) : GetCityList {
    override suspend fun invoke(): Flow<Response<List<String>>> = repository.getCityList()

}