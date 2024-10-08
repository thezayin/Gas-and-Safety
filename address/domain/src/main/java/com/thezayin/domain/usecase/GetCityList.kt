package com.thezayin.domain.usecase

import com.thezayin.domain.repository.AreaRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOn

/**
 * Use case for retrieving a list of cities.
 *
 * This interface defines the contract for fetching all available cities from the data source.
 * Implementations of this use case should handle the necessary business logic and data interactions.
 */
interface GetCityListUseCase : suspend () -> Flow<Response<List<String>>>

/**
 * Implementation of the [GetCityListUseCase] interface.
 *
 * This class handles the retrieval of city lists by interacting with the [AreaRepository].
 *
 * @property areaRepository The repository responsible for area-related data operations.
 */
class GetCityListUseCaseImpl(
    private val areaRepository: AreaRepository
) : GetCityListUseCase {

    /**
     * Invokes the use case to fetch the list of cities.
     *
     * @return A [Flow] emitting a [Response] containing a list of city names on success
     *         or an error message on failure.
     */
    override suspend fun invoke(): Flow<Response<List<String>>> = flow {
        emit(Response.Loading) // Indicate that the operation has started
        try {
            // Fetch the city list from the repository
            val responseFlow = areaRepository.getCityList()
            emitAll(responseFlow) // Emit the response from the repository
        } catch (e: Exception) {
            // Emit an error response if fetching cities fails
            emit(Response.Error("Failed to retrieve city list: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO) // Perform the operation on the IO dispatcher
}