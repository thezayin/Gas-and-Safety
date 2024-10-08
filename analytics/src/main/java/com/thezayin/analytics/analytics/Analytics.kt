package com.thezayin.analytics.analytics

import com.thezayin.analytics.events.AnalyticsEvent

/**
 * Interface for Analytics functionality.
 *
 * This interface defines a contract for logging analytics events in the application.
 * Implementations of this interface should provide the logic for capturing and reporting
 * various events that occur within the application.
 */
interface Analytics {
    /**
     * Logs an analytics event.
     *
     * @param event The [AnalyticsEvent] to be logged. This event should contain relevant data
     *               that needs to be captured for analytics purposes.
     */
    fun logEvent(event: AnalyticsEvent)
}