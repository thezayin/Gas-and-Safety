package com.thezayin.useraddress.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AreaRepository {
    suspend fun getAreaList(city:String): Flow<Response<List<String>>>
    suspend fun getCityList(): Flow<Response<List<String>>>
}