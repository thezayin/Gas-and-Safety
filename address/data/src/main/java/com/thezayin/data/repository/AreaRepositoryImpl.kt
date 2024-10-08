package com.thezayin.data.repository

import com.thezayin.domain.repository.AreaRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the AreaRepository interface.
 * Provides methods to fetch lists of cities and their corresponding areas.
 */
class AreaRepositoryImpl : AreaRepository {

    /**
     * Fetches a list of areas based on the provided city name.
     *
     * @param city The name of the city for which to retrieve areas.
     * @return A Flow emitting Response objects containing the list of areas or an error.
     */
    override suspend fun getAreaList(city: String): Flow<Response<List<String>>> = flow {
        try {
            // Emit a loading state to indicate data fetching has started
            emit(Response.Loading)

            // Determine the list of areas based on the city name
            val areaList = getAreasByCity(city)

            // Emit the successful response with the retrieved area list
            emit(Response.Success(areaList))
        } catch (e: Exception) {
            // Emit an error state with the exception message if something goes wrong
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    /**
     * Fetches a list of available cities.
     *
     * @return A Flow emitting Response objects containing the list of cities or an error.
     */
    override suspend fun getCityList(): Flow<Response<List<String>>> = flow {
        try {
            // Emit a loading state to indicate data fetching has started
            emit(Response.Loading)

            // Define the list of available cities
            val cityList = listOf("Select", "Islamabad", "Rawalpindi")

            // Emit the successful response with the retrieved city list
            emit(Response.Success(cityList))
        } catch (e: Exception) {
            // Emit an error state with the exception message if something goes wrong
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    /**
     * Helper function to retrieve areas based on the city name.
     *
     * @param city The name of the city.
     * @return A list of area names corresponding to the city.
     */
    private fun getAreasByCity(city: String): List<String> {
        return when (city) {
            "Islamabad" -> islamabadAreas
            "Rawalpindi" -> rawalpindiAreas
            else -> defaultAreas
        }
    }

    // Predefined list of areas for Islamabad
    private val islamabadAreas = listOf(
        "Select",
        "PWD",
        "DHA Ammar",
        "Korang Town",
        "DHA Phase 1",
        "DHA Phase 2",
        "DHA Phase 3",
        "DHA Phase 4",
        "DHA Phase 5",
        "Sowan Garden",
        "Jinnah Garden",
        "Pakistan Town"
    )

    // Predefined list of areas for Rawalpindi
    private val rawalpindiAreas = listOf(
        "Select",
        "Bahria Phase 8",
        "Bahria Phase 7",
        "Bahria Phase 6",
        "Bahria Phase 5",
        "Bahria Phase 4",
        "Bahria Phase 3",
        "Bahria Phase 2",
        "Bahria Phase 1"
    )

    // Default list of areas for cities other than Islamabad and Rawalpindi
    private val defaultAreas = listOf("Area1", "Area2", "Area3")
}