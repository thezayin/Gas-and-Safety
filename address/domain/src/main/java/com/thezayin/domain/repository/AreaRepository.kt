package com.thezayin.domain.repository

import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing geographical data related to cities and their respective areas.
 *
 * This interface defines the contract for retrieving lists of cities and areas within a specific city.
 * Implementations of this repository should handle data fetching from various data sources such as
 * remote APIs, local databases, or other storage mechanisms.
 */
interface AreaRepository {

    /**
     * Retrieves a list of areas within the specified city.
     *
     * This method fetches all the areas associated with the given city name. The data can originate
     * from a remote server, a local database, or any other data source depending on the implementation.
     *
     * @param city The name of the city for which to retrieve the list of areas.
     * @return A [Flow] emitting a [Resource] containing a list of area names on success or an error message on failure.
     */
    suspend fun getAreaList(city: String): Flow<Resource<List<String>>>

    /**
     * Retrieves a list of all available cities.
     *
     * This method fetches all the cities that are available in the system. The data source can vary
     * based on the implementation, such as fetching from a remote API or a local database.
     *
     * @return A [Flow] emitting a [Resource] containing a list of city names on success or an error message on failure.
     */
    suspend fun getCityList(): Flow<Resource<List<String>>>
}