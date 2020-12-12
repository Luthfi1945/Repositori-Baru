package com.example.applogin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.applogin.R
import com.example.applogin.data.repository.AuthRepository
import com.example.applogin.databinding.FragmentSplashFargBinding
import kotlinx.coroutines.*


class SplashFarg : Fragment() {
    val parent: AuthActivity by lazy { activity as AuthActivity }
    val viewModel:AuthViewModel by lazy { AuthViewModel (AuthRepository(parent)) }
    lateinit var  binding: FragmentSplashFargBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSplashFargBinding.inflate(inflater, container, false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        return binding.root
    }
    private fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            delay( 2000)
            withContext(Dispatchers.Main) {
                if (viewModel.isLogin.value == true) {
                    parent.onSuccess(viewModel.authUser.value)
                } else {
                    findNavController().navigate(SplashFargDirections.actionSplashFargToLoginFragment())
                }
            }
        }
    }
}