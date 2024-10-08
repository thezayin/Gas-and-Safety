package com.thezayin.analytics.analytics

import com.thezayin.analytics.events.AnalyticsEvent

interface Analytics {
    fun logEvent(event: AnalyticsEvent)
}