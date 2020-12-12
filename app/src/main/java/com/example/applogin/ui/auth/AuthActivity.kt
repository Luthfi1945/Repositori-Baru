package com.example.applogin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.applogin.R
import com.example.applogin.data.model.AuthUser
import com.example.applogin.data.repository.AuthRepository
import com.example.applogin.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_auth)
    }
    fun onSuccess(authUser : AuthUser?) {
        startActivity(intent)
        finish()
    }
}