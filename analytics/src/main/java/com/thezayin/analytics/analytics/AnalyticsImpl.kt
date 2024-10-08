package com.thezayin.analytics.analytics

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.analytics.events.AnalyticsEvent

/**
 * Implementation of the [Analytics] interface that logs events to a Firebase backend.
 *
 * This class provides the actual functionality to log analytics events
 * using the Firebase Analytics service. It captures the event name
 * and any associated arguments for reporting.
 *
 * @property analytics The instance of [FirebaseAnalytics] used to log events.
 */
class AnalyticsImpl(
    private val analytics: FirebaseAnalytics,
) : Analytics {

    @SuppressLint("BinaryOperationInTimber") // Suppresses the lint warning for logging binary operations
    override fun logEvent(event: AnalyticsEvent) {
        // Log the event name and arguments to the console for debugging purposes
        Log.d(
            "Analytics",
            "FirebaseAnalyticsRepository eventName: ${event.event}, arguments: ${event.args}"
        )

        // Ensure the event name is not null before logging to Firebase
        event.event?.let { eventName ->
            analytics.logEvent(eventName, event.args) // Log the event with Firebase Analytics
        }
    }
}