package com.example.servivelog.ui.gestioncomputadora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentGestionarComputadoraBinding

class GestionarComputadora : Fragment() {

    private lateinit var gestionarComputadoraBinding: FragmentGestionarComputadoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        gestionarComputadoraBinding = FragmentGestionarComputadoraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return gestionarComputadoraBinding.root
    }
}