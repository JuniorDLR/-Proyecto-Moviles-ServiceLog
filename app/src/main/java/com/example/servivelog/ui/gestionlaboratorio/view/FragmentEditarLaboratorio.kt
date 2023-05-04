package com.example.servivelog.ui.gestionlaboratorio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentEditarDiagnosticoBinding
import com.example.servivelog.databinding.FragmentEditarLaboratorioBinding


class FragmentEditarLaboratorio : Fragment() {
    private lateinit var editarLaboratoriosBinding: FragmentEditarLaboratorioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editarLaboratoriosBinding = FragmentEditarLaboratorioBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val btnEditar = editarLaboratoriosBinding.btnEditar

        btnEditar.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        return editarLaboratoriosBinding.root
    }


}