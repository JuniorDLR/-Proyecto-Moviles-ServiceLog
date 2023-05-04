package com.example.servivelog.ui.gestiondiagnostico.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentEditarDiagnosticoBinding

class FragmentEditarDiagnostico : Fragment() {

    private lateinit var editarDiagnosticoBinding: FragmentEditarDiagnosticoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editarDiagnosticoBinding = FragmentEditarDiagnosticoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val btnEditar = editarDiagnosticoBinding.btnEditar

        btnEditar.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        return editarDiagnosticoBinding.root
    }


}