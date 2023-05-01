package com.example.servivelog.ui.gestionmantenimiento.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.servivelog.databinding.FragmentAgregarMantenimientoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAgregarMantenimiento : Fragment() {

            private lateinit var agregarMantenimientoBinding: FragmentAgregarMantenimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        agregarMantenimientoBinding = FragmentAgregarMantenimientoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return agregarMantenimientoBinding.root
    }
}