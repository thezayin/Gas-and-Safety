package com.thezayin.analytics.analytics

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.analytics.events.AnalyticsEvent

/**
 * Implementation of `Analytics` which logs events
 * to a Firebase backend.
 */
class AnalyticsImpl(
    private val analytics: FirebaseAnalytics,
) : Analytics {

    @SuppressLint("BinaryOperationInTimber")
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(
            "Analytics",
            "FirebaseAnalyticsRepository eventName...." + event.event + " arguments... " + event.args + " "
        )
        event.event?.let { eventName ->
            analytics.logEvent(eventName, event.args)
        }
    }
}