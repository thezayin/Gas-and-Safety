package com.thezayin.analytics.utils

object AnalyticsConstant {
    const val ANALYTICS_TAG = "AnalyticsTAG"

    // Cart-related event constants
    const val CART_ITEM_ADDED = "CartItemAdded" // Event for when an item is added to the cart
    const val CART_ITEM_REMOVED = "CartItemRemoved" // Event for when an item is removed from the cart
    const val CART_CLEARED = "CartCleared" // Event for when the cart is cleared
    const val CART_CHECKOUT_INITIATED = "CartCheckoutInitiated" // Event for when checkout is started
    const val CART_VIEWED = "CartViewed" // Event for when the cart is viewed

    // Miscellaneous event constants
    const val SCREEN_VIEW = "ScreenView" // Event for tracking screen views
    const val SERVER_SELECTION = "ServerSelection" // Event for selecting a server
    const val SETTINGS_RATE_US = "SettingsRateUs" // Event for rating the app
    const val SETTINGS_FEEDBACK = "SettingsFeedback" // Event for submitting feedback
    const val SETTINGS_CONTACT_US = "SettingsContactUs" // Event for contacting support
    const val SETTINGS_TERMS_CONDITION = "SettingsTermsConditions" // Event for viewing terms and conditions
    const val SETTINGS_PRIVACY_POLICY = "SettingsPrivacyPolicy" // Event for viewing the privacy policy
    const val SETTINGS_MORE_APPS = "SettingsMoreApps" // Event for viewing more apps
}
