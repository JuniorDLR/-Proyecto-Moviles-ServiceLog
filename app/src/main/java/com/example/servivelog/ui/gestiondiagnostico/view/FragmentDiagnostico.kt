package com.example.servivelog.ui.gestiondiagnostico.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.Navigation
import com.example.servivelog.R

import com.example.servivelog.databinding.FragmentDiagnosticoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentDiagnostico : Fragment() {
    private lateinit var FragmentDiagnostico: FragmentDiagnosticoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentDiagnostico = FragmentDiagnosticoBinding.inflate(layoutInflater)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentDiagnostico = FragmentDiagnosticoBinding.inflate(layoutInflater)
        val btnAgregar = FragmentDiagnostico.fbtnagregar

        btnAgregar.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_fragmentDiagnostico_to_fragmentAgregarDiagnostico)
        }

        return FragmentDiagnostico.root
    }


}