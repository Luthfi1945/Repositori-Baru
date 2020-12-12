package com.example.applogin.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.applogin.data.local.AuthPrev
import com.example.applogin.data.model.ActionState
import com.example.applogin.data.model.AuthUser

class AuthRepository (val context: Context) {
    private val authPrev: AuthPrev by lazy {AuthPrev(context)}

    val  authUser = MutableLiveData<AuthUser>(authPrev.authUser)
    val isLogin = MutableLiveData<Boolean>(authPrev.isLogin)

    suspend fun login (email: String, password: String):ActionState<AuthUser>{
        return authPrev.login(email, password)
    }
    suspend fun register (user: AuthUser):ActionState<AuthUser>{
        return authPrev.register(user)
    }
    suspend fun logout():ActionState<Boolean>{
        return authPrev.logout()
    }

}