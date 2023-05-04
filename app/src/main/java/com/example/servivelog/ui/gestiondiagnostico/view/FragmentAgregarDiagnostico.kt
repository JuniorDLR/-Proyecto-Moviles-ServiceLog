package com.example.servivelog.ui.gestiondiagnostico.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentAgregarDiagnosticoBinding

class FragmentAgregarDiagnostico : Fragment() {

    private lateinit var agregarDiagnosticoBinding: FragmentAgregarDiagnosticoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agregarDiagnosticoBinding = FragmentAgregarDiagnosticoBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val btnAgregar = agregarDiagnosticoBinding.btnGuardar

        btnAgregar.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        return agregarDiagnosticoBinding.root
    }


}