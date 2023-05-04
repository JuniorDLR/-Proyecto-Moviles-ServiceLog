package com.example.servivelog.ui.gestioncomputadora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentAgregarComputadoraBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAgregarComputadora : Fragment() {

    private lateinit var agregarComputadoraBinding: FragmentAgregarComputadoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        agregarComputadoraBinding = FragmentAgregarComputadoraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return agregarComputadoraBinding.root
    }
}