package com.thezayin.analytics.events

import android.os.Bundle
import com.thezayin.analytics.utils.AnalyticsConstant.CART_CHECKOUT_INITIATED
import com.thezayin.analytics.utils.AnalyticsConstant.CART_CLEARED
import com.thezayin.analytics.utils.AnalyticsConstant.CART_ITEM_ADDED
import com.thezayin.analytics.utils.AnalyticsConstant.CART_ITEM_REMOVED
import com.thezayin.analytics.utils.AnalyticsConstant.CART_VIEWED

/**
 * Sealed class representing different analytics events related to user interactions.
 *
 * Each subclass corresponds to a specific event that can be logged to the analytics service.
 *
 * @property event The event name to be logged.
 * @property args A Bundle containing any associated parameters for the event.
 */
sealed class AnalyticsEvent(
    val event: String? = null,
    val args: Bundle?
) {
    /**
     * Event representing the addition of an item to the cart.
     *
     * @param itemId The ID of the item being added to the cart.
     * @param quantity The quantity of the item being added.
     */
    class CartItemAddedEvent(
        itemId: String,
        quantity: Int
    ) : AnalyticsEvent(
        CART_ITEM_ADDED,
        Bundle().apply {
            putString("item_id", itemId)
            putInt("quantity", quantity)
        }
    )

    /**
     * Event representing the removal of an item from the cart.
     *
     * @param itemId The ID of the item being removed from the cart.
     */
    class CartItemRemovedEvent(
        itemId: String
    ) : AnalyticsEvent(
        CART_ITEM_REMOVED,
        Bundle().apply {
            putString("item_id", itemId)
        }
    )

    /**
     * Event representing the clearing of the cart.
     */
    class CartClearedEvent : AnalyticsEvent(
        CART_CLEARED,
        null
    )

    /**
     * Event representing the initiation of a checkout process.
     *
     * @param totalAmount The total amount for the checkout.
     */
    class CartCheckoutInitiatedEvent(
        totalAmount: Double
    ) : AnalyticsEvent(
        CART_CHECKOUT_INITIATED,
        Bundle().apply {
            putDouble("total_amount", totalAmount)
        }
    )

    /**
     * Event representing the viewing of the cart.
     */
    class CartViewedEvent : AnalyticsEvent(
        CART_VIEWED,
        null
    )
}