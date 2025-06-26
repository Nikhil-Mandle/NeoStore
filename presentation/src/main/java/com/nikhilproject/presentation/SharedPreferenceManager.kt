package com.nikhilproject.presentation

import android.content.Context
import com.nikhilproject.presentation.utils.Constant.ACCESS_TOKEN
import com.nikhilproject.presentation.utils.Constant.PROFILE_PIC
import com.nikhilproject.presentation.utils.Constant.SHARED_PREFERENCE_NAME
import com.nikhilproject.presentation.utils.Constant.USER_EMAIL
import com.nikhilproject.presentation.utils.Constant.USER_NAME
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(private val context: Context) {

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