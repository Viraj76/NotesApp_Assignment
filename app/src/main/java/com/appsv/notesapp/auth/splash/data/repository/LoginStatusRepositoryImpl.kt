package com.appsv.notesapp.auth.splash.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.appsv.notesapp.auth.splash.domain.repository.LoginStatusRepository

class LoginStatusRepositoryImpl(context: Context) : LoginStatusRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    override fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    override fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", loggedIn).apply()
    }
}
