package com.thezayin.useraddress.data.repository

import com.thezayin.framework.utils.Response
import com.thezayin.useraddress.domain.repository.AreaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AreaRepositoryImpl : AreaRepository {
    override suspend fun getAreaList(city: String): Flow<Response<List<String>>> = flow {
        try {
            emit(Response.Loading)
            val list = when (city) {
                "Islamabad" -> listOf(
                    "Select",
                    "DHA Phase 1",
                    "DHA Phase 2",
                )

                "Rawalpindi" -> listOf(
                    "Select",
                    "Bahria Phase 8",
                    "Bahria Phase 7",
                    "Bahria Phase 6",
                    "Bahria Phase 5",
                    "Bahria Phase 4",
                    "Bahria Phase 3",
                    "Bahria Phase 2",
                    "Bahria Phase 1",
                )

                else -> {
                    listOf("Area1", "Area2", "Area3")
                }
            }
            emit(Response.Success(list))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error"))
        }
    }

    override suspend fun getCityList(): Flow<Response<List<String>>> = flow {
        try {
            emit(Response.Loading)
            val list = listOf("Select", "Islamabad", "Rawalpindi")
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error"))
        }
    }
}