package com.thezayin.analytics.events

import android.os.Bundle
import com.thezayin.analytics.utils.AnalyticsConstant.AD_REVENUE
import com.thezayin.analytics.utils.AnalyticsConstant.APP_OPEN_AD
import com.thezayin.analytics.utils.AnalyticsConstant.INTERSTITIAL_AD
import com.thezayin.analytics.utils.AnalyticsConstant.NATIVE_AD
import com.thezayin.analytics.utils.AnalyticsConstant.SCREEN_VIEW
import com.thezayin.analytics.utils.AnalyticsConstant.SEARCH_NUMBER_CLICK
import com.thezayin.analytics.utils.AnalyticsConstant.SERVER_SELECTION
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_CONTACT_US
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_FEEDBACK
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_MORE_APPS
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_PRIVACY_POLICY
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_RATE_US
import com.thezayin.analytics.utils.AnalyticsConstant.SETTINGS_TERMS_CONDITION

sealed class AnalyticsEvent(
    val event: String? = null,
    val args: Bundle?
) {
    /**
     * A key-value pair used to supply extra context to an
     * analytics event.
     *
     * @param key - the parameter key. Wherever possible use
     * one of the standard `ParamKeys`, however, if no suitable
     * key is available you can define your own as long as it is
     * configured in your backend analytics system (for example,
     * by creating a Firebase Analytics custom parameter).
     *
     * @param value - the parameter value.
     */
    class AppOpenAdEvent(
        status: String
    ) : AnalyticsEvent(
        APP_OPEN_AD,
        Bundle().apply {
            putString("status", status)
        }
    )

    class InterstitialAdEvent(
        status: String
    ) : AnalyticsEvent(
        INTERSTITIAL_AD,
        Bundle().apply {
            putString("status", status)
        }
    )

    class AdPaidEvent(
        event: String,
        provider: String,
        value: String
    ) : AnalyticsEvent(
        AD_REVENUE,
        Bundle().apply {
            putString("event", event)
            putString("provider", provider)
            putString("price", value)
        }
    )

    class NativeAdEvent(
        status: String
    ) : AnalyticsEvent(
        NATIVE_AD,
        Bundle().apply {
            putString("status", status)
        }
    )

    class ScreenViewEvent(
        status: String
    ) : AnalyticsEvent(
        SCREEN_VIEW,
        Bundle().apply {
            putString("status", status)
        }
    )

    class ServerSelectionEvent(
        status: String
    ) : AnalyticsEvent(
        SERVER_SELECTION,
        Bundle().apply {
            putString("url", status)
        }
    )

    class AdImpressionEvent(event: String, provider: String) : AnalyticsEvent(
        event,
        Bundle().apply {
            putString("ad_provider", provider)
        }
    )

    class SettingsRateUs(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_RATE_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsFeedback(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_FEEDBACK,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsContactUs(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_CONTACT_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsTermsConditions(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_TERMS_CONDITION,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingsPrivacyPolicy(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_PRIVACY_POLICY,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SearchNumberClick(
        status: String
    ) : AnalyticsEvent(
        SEARCH_NUMBER_CLICK,
        Bundle().apply {
            putString("status", status)
        }
    )

    class SettingMoreApps(
        status: String
    ) : AnalyticsEvent(
        SETTINGS_MORE_APPS,
        Bundle().apply {
            putString("status", status)
        }
    )
}