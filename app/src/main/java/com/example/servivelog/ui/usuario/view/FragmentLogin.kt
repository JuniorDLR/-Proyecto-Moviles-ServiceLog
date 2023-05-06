package com.example.servivelog.ui.usuario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentLoginBinding
import com.example.servivelog.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLogin : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        loginBinding.btnIniciar.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_fragmentLogin_to_fragmentMenu)
            (activity as MainActivity).findViewById<BottomNavigationView>(R.id.BarraNavegacion).isVisible = true //esto funciona para mostrar el navigation view despues de iniciar sesion
        }

        // Inflate the layout for this fragment
        return loginBinding.root
    }
}