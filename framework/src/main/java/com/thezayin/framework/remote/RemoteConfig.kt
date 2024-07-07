package com.thezayin.framework.remote

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val SHOW_AD_ON_APP_OPEN = "appOpenAd"

private const val SHOW_AD_ON_SEARCH_CLICK = "searchClickAd"
private const val SHOW_AD_ON_HOME_SCREEN_NATIVE = "homeScreenNativeAd"
private const val SHOW_AD_ON_HOME_SCREEN_IAP_SELECTION = "homeScreenIAPSelectionAd"
private const val SHOW_AD_ON_HOME_SCREEN_VPN_SELECTION = "homeScreenVPNSelectionAd"
private const val SHOW_AD_ON_HOME_SCREEN_SERVER_SELECTION = "homeScreenServerSelectionAd"
private const val SHOW_AD_ON_HOME_SCREEN_SETTING_SELECTION = "homeScreenSettingSelectionAd"
private const val SHOW_AD_ON_HOME_SCREEN_LOADING_DIALOG = "homeScreenLoadingDialogNativeAd"

private const val SHOW_AD_ON_SERVER_SCREEN_NATIVE = "serverScreenNativeAd"
private const val SHOW_AD_ON_SERVER_SCREEN_VPN_SELECTION = "serverScreenVPNSelectionAd"
private const val SHOW_AD_ON_SERVER_SCREEN_IAP_SELECTION = "serverScreenIAPSelectionAd"
private const val SHOW_AD_ON_SERVER_SCREEN_BACK_SELECTION = "serverScreenBackSelectionAd"
private const val SHOW_AD_ON_SERVER_SCREEN_SERVER_SELECTION = "serverScreenServerSelectionAd"
private const val SHOW_AD_ON_SERVER_SCREEN_LOADING_DIALOG = "serverScreenLoadingDialogNativeAd"

private const val SHOW_AD_ON_RESULT_SCREEN_NATIVE = "resultScreenNativeAd"
private const val SHOW_AD_ON_RESULT_SCREEN_IAP_SELECTION = "resulScreenIAPSelectionAd"
private const val SHOW_AD_ON_RESULT_SCREEN_VPN_SELECTION = "resultScreenVPNSelectionAd"
private const val SHOW_AD_ON_RESULT_SCREEN_BACK_SELECTION = "resultScreenBackSelectionAd"

private const val SHOW_AD_ON_WEB_SCREEN_NATIVE = "webScreenNativeAd"
private const val SHOW_AD_ON_WEB_SCREEN_VPN_SELECTION = "webScreenVPNSelectionAd"
private const val SHOW_AD_ON_WEB_SCREEN_IAP_SELECTION = "webScreenIAPSelectionAd"
private const val SHOW_AD_ON_WEB_SCREEN_BACK_SELECTION = "webScreenBackSelectionAd"
private const val SHOW_AD_ON_WEB_SCREEN_LOADING_DIALOG = "webScreenLoadingDialogNativeAd"

private const val SHOW_AD_ON_SETTING_SCREEN_NATIVE = "settingScreenNativeAd"
private const val SHOW_AD_ON_SETTING_SCREEN_VPN_SELECTION = "settingScreenVPNSelectionAd"
private const val SHOW_AD_ON_SETTING_SCREEN_IAP_SELECTION = "settingScreenIAPSelectionAd"
private const val SHOW_AD_ON_SETTING_SCREEN_BACK_SELECTION = "settingScreenBackSelectionAd"

private const val INIT_ADS = "init_ads"
private const val SHOW_PREMIUM_BUTTON = "showPremiumButton"

private const val SERVER_URL_FIRST = "server_1_url"
private const val SERVER_URL_SECOND = "server_2_url"
private const val SERVER_URL_THIRD = "server_3_url"
private const val SERVER_URL_FOURTH = "server_4_url"
private const val SERVER_URL_FIFTH = "server_5_url"

@Suppress("DEPRECATION")
class RemoteConfig {
    private val default: Map<String, Any> = mapOf(
        INIT_ADS to true,
        SHOW_AD_ON_APP_OPEN to true,
        SHOW_PREMIUM_BUTTON to true,
        SERVER_URL_FIRST to "https://simdatabase.info/search",
        SERVER_URL_SECOND to "https://simownerdetail.com/search",
        SERVER_URL_THIRD to "https://pakdb.xyz/sim-ownership/",
        SERVER_URL_FOURTH to "https://pakdb.xyz/sim-ownership/",
        SERVER_URL_FIFTH to "https://pakdb.xyz/sim-ownership/",
        SHOW_AD_ON_SEARCH_CLICK to true,
        SHOW_AD_ON_HOME_SCREEN_NATIVE to true,
        SHOW_AD_ON_HOME_SCREEN_IAP_SELECTION to true,
        SHOW_AD_ON_HOME_SCREEN_VPN_SELECTION to true,
        SHOW_AD_ON_HOME_SCREEN_SERVER_SELECTION to true,
        SHOW_AD_ON_HOME_SCREEN_SETTING_SELECTION to true,
        SHOW_AD_ON_HOME_SCREEN_LOADING_DIALOG to true,
        SHOW_AD_ON_SERVER_SCREEN_NATIVE to true,
        SHOW_AD_ON_SERVER_SCREEN_VPN_SELECTION to true,
        SHOW_AD_ON_SERVER_SCREEN_IAP_SELECTION to true,
        SHOW_AD_ON_SERVER_SCREEN_BACK_SELECTION to true,
        SHOW_AD_ON_SERVER_SCREEN_SERVER_SELECTION to true,
        SHOW_AD_ON_SERVER_SCREEN_LOADING_DIALOG to true,
        SHOW_AD_ON_RESULT_SCREEN_NATIVE to true,
        SHOW_AD_ON_RESULT_SCREEN_IAP_SELECTION to true,
        SHOW_AD_ON_RESULT_SCREEN_VPN_SELECTION to true,
        SHOW_AD_ON_RESULT_SCREEN_BACK_SELECTION to true,
        SHOW_AD_ON_WEB_SCREEN_NATIVE to true,
        SHOW_AD_ON_WEB_SCREEN_VPN_SELECTION to true,
        SHOW_AD_ON_WEB_SCREEN_IAP_SELECTION to true,
        SHOW_AD_ON_WEB_SCREEN_BACK_SELECTION to true,
        SHOW_AD_ON_WEB_SCREEN_LOADING_DIALOG to true,
        SHOW_AD_ON_SETTING_SCREEN_NATIVE to true,
        SHOW_AD_ON_SETTING_SCREEN_VPN_SELECTION to true,
        SHOW_AD_ON_SETTING_SCREEN_IAP_SELECTION to true,
        SHOW_AD_ON_SETTING_SCREEN_BACK_SELECTION to true,
    )

    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "fetchAndActivate: ${all.mapValues { (_, v) -> v.asString() }}")
        }
    }

    val getFirstUrl: String
        get() = config[SERVER_URL_FIRST].asString()

    val getSecondUrl: String
        get() = config[SERVER_URL_SECOND].asString()

    val getThirdUrl: String
        get() = config[SERVER_URL_THIRD].asString()

    val getFourthUrl: String
        get() = config[SERVER_URL_FOURTH].asString()

    val getFifthUrl: String
        get() = config[SERVER_URL_FIFTH].asString()

    val initAds: Boolean
        get() = config[INIT_ADS].asBoolean()

    val showPremiumButton: Boolean
        get() = config[SHOW_PREMIUM_BUTTON].asBoolean()

    val showAdOnAppOpen: Boolean
        get() = config[SHOW_AD_ON_APP_OPEN].asBoolean()

    val showAdOnSearchClick: Boolean
        get() = config[SHOW_AD_ON_SEARCH_CLICK].asBoolean()

    val showAdOnHomeScreenNative: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_NATIVE].asBoolean()

    val showAdOnHomeScreenIAPSelection: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_IAP_SELECTION].asBoolean()

    val showAdOnHomeScreenVPNSelection: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_VPN_SELECTION].asBoolean()

    val showAdOnHomeScreenServerSelection: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_SERVER_SELECTION].asBoolean()

    val showAdOnHomeScreenSettingSelection: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_SETTING_SELECTION].asBoolean()

    val showAdOnHomeScreenLoadingDialog: Boolean
        get() = config[SHOW_AD_ON_HOME_SCREEN_LOADING_DIALOG].asBoolean()

    val showAdOnServerScreenNative: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_NATIVE].asBoolean()

    val showAdOnServerScreenVPNSelection: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_VPN_SELECTION].asBoolean()

    val showAdOnServerScreenIAPSelection: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_IAP_SELECTION].asBoolean()

    val showAdOnServerScreenBackSelection: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_BACK_SELECTION].asBoolean()

    val showAdOnServerScreenServerSelection: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_SERVER_SELECTION].asBoolean()

    val showAdOnServerScreenLoadingDialog: Boolean
        get() = config[SHOW_AD_ON_SERVER_SCREEN_LOADING_DIALOG].asBoolean()

    val showAdOnResultScreenNative: Boolean
        get() = config[SHOW_AD_ON_RESULT_SCREEN_NATIVE].asBoolean()

    val showAdOnResultScreenIAPSelection: Boolean
        get() = config[SHOW_AD_ON_RESULT_SCREEN_IAP_SELECTION].asBoolean()

    val showAdOnResultScreenVPNSelection: Boolean
        get() = config[SHOW_AD_ON_RESULT_SCREEN_VPN_SELECTION].asBoolean()

    val showAdOnResultScreenBackSelection: Boolean
        get() = config[SHOW_AD_ON_RESULT_SCREEN_BACK_SELECTION].asBoolean()

    val showAdOnWebScreenNative: Boolean
        get() = config[SHOW_AD_ON_WEB_SCREEN_NATIVE].asBoolean()

    val showAdOnWebScreenVPNSelection: Boolean
        get() = config[SHOW_AD_ON_WEB_SCREEN_VPN_SELECTION].asBoolean()

    val showAdOnWebScreenIAPSelection: Boolean
        get() = config[SHOW_AD_ON_WEB_SCREEN_IAP_SELECTION].asBoolean()

    val showAdOnWebScreenBackSelection: Boolean
        get() = config[SHOW_AD_ON_WEB_SCREEN_BACK_SELECTION].asBoolean()

    val showAdOnWebScreenLoadingDialog: Boolean
        get() = config[SHOW_AD_ON_WEB_SCREEN_LOADING_DIALOG].asBoolean()

    val showAdOnSettingScreenNative: Boolean
        get() = config[SHOW_AD_ON_SETTING_SCREEN_NATIVE].asBoolean()

    val showAdOnSettingScreenVPNSelection: Boolean
        get() = config[SHOW_AD_ON_SETTING_SCREEN_VPN_SELECTION].asBoolean()

    val showAdOnSettingScreenIAPSelection: Boolean
        get() = config[SHOW_AD_ON_SETTING_SCREEN_IAP_SELECTION].asBoolean()

    val showAdOnSettingScreenBackSelection: Boolean
        get() = config[SHOW_AD_ON_SETTING_SCREEN_BACK_SELECTION].asBoolean()

}
