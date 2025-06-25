package com.nikhilproject.presentation

import android.content.Context
import javax.inject.Inject

const val SHARED_PREFERENCE_NAME = "NeostorePref"
const val ACCESS_TOKEN = "access_token"
const val USER_NAME = "user_name"
const val USER_EMAIL = "user_email"
const val PROFILE_PIC = "profile_pic"

// TODO: Need to change name to SharedPreference
class TokenManager @Inject constructor(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun addAccessToken(accessToken: String) {
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun saveUserName(name: String) {
        editor.putString(USER_NAME, name)
        editor.apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(USER_NAME, null)
    }

    fun saveUserEmail(email: String) {
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(USER_EMAIL, null)
    }

    fun saveProfilePic(profilePic: String) {
        editor.putString(PROFILE_PIC, profilePic)
        editor.apply()
    }

    fun getProfilePic(): String? {
        return sharedPreferences.getString(PROFILE_PIC, null)
    }

    fun clearSession() {
        editor.remove(ACCESS_TOKEN)
        editor.remove(USER_NAME)
        editor.remove(USER_EMAIL)
        editor.apply()
    }

}