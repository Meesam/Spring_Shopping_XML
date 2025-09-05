package com.meesam.springshoppingxml.utils

import android.content.Context
import android.content.SharedPreferences
import com.meesam.springshoppingxml.utils.Constants.PREFS_TOKEN_FILE
import com.meesam.springshoppingxml.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import androidx.core.content.edit

class TokenManager {
    class TokenManager @Inject constructor(@ApplicationContext context: Context) {
        private var prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

        fun saveToken(token: String) {
            prefs.edit {
                putString(USER_TOKEN, token)
            }
        }

        fun getToken(): String? {
            return prefs.getString(USER_TOKEN, null)
        }
    }
}