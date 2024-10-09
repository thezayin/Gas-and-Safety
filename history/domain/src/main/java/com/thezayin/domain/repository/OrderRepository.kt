package com.thezayin.domain.repository

import com.thezayin.domain.model.OrderModel
import com.thezayin.framework.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface that defines the contract for a repository responsible for fetching user orders.
 * This repository will be used to abstract the data layer and provide a consistent way to access orders.
 */
interface HistoryRepository {

    /**
     * Fetches the list of orders for a specific user asynchronously.
     *
     * @param userId The unique identifier of the user whose orders are to be retrieved.
     * @return A Flow emitting a Resource containing a list of OrderModel objects.
     *         The Resource can represent Loading, Success, or Error states.
     */
    fun fetchUserOrders(userId: String): Flow<Resource<List<OrderModel>>>
}