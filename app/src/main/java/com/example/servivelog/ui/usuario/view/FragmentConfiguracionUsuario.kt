package com.example.servivelog.ui.usuario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentConfiguracionUsuarioBinding



class FragmentConfiguracionUsuario : Fragment() {
    private lateinit var ConfiguracionUsaurioBinding: FragmentConfiguracionUsuarioBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConfiguracionUsaurioBinding = FragmentConfiguracionUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return ConfiguracionUsaurioBinding.root
    }


}