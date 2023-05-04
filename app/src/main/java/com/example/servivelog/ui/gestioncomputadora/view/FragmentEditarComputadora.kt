package com.example.servivelog.ui.gestioncomputadora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentEditarComputadoraBinding

class FragmentEditarComputadora : Fragment() {

    private lateinit var editarComputadoraBinding: FragmentEditarComputadoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        editarComputadoraBinding = FragmentEditarComputadoraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        return editarComputadoraBinding.root
    }
}