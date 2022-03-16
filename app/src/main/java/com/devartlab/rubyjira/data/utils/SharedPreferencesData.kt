package com.devartlab.rubyjira.data.utils

object SharedPreferencesData {
    fun getLanguage() = SharedPreferencesUtils.getString("_language", null)
    fun setLanguage(value: String) = SharedPreferencesUtils.saveString("_language", value)

    fun isFirstOpen() = SharedPreferencesUtils.getBoolean("_first_open", true)
    fun setIsFirstOpen(value: Boolean) = SharedPreferencesUtils.saveBoolean("_first_open", value)

    fun setAuthToken(value: String) = SharedPreferencesUtils.saveString("_auth_token", value)
    fun getAuthToken() = SharedPreferencesUtils.getString("_auth_token", null)

    fun isVerifyCode() = SharedPreferencesUtils.getBoolean("_verify_code", false)
    fun setIsVerifyCode(value: Boolean) = SharedPreferencesUtils.saveBoolean("_verify_code", value)

    fun logout(){
        SharedPreferencesUtils.clear()
    }
}