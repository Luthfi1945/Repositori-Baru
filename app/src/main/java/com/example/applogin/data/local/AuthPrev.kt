package com.example.applogin.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.applogin.data.model.ActionState
import com.example.applogin.data.model.AuthUser
import com.example.applogin.util.getObject
import com.example.applogin.util.putObject

class AuthPrev (val context: Context) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(AuthPrev::class.java.name, Context.MODE_PRIVATE)
    }

    private companion object {
        const val USER = "user"
        const val IS_LOGIN = "is_login"
    }
    var authUser:AuthUser?
    get() = sp.getObject(USER)
    private set(value) = sp.edit().putObject(USER, value).apply()

    var isLogin: Boolean
    get() = sp.getBoolean(IS_LOGIN,false)
    private set(value) = sp.edit().putBoolean(IS_LOGIN,value).apply()

    suspend fun login(email: String,password: String): ActionState<AuthUser> {
        val user = authUser
        return if (user == null) {
            ActionState(message = "Anda belum terdaftar",isSuccess = false)
        } else if (email.isBlank()|| password.isBlank()) {
            ActionState(message = "Email dan password tidak boleh kosong", isSuccess = false )
        } else if (user.email == email && user.password == password) {
            isLogin = true
            ActionState(authUser, message = "Anda Berhasil Login")
        }else{
            ActionState(message = "Email atau password salah", isSuccess = false)
        }
    }
    suspend fun register (user: AuthUser): ActionState<AuthUser> {
        return if (user.email.isBlank() || user.password.isBlank()) {
            ActionState(message = "Email dan password tidal boleh kosong", isSuccess = false)
        }else{
            authUser = user
            ActionState(user, message = "Anda berhasil daftar")
        }
    }
    suspend fun logout(): ActionState<Boolean> {
        isLogin = false
        return ActionState(true,message = "Anda berhasil logout")
    }

}