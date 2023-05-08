package com.example.servivelog.ui.gestionlaboratorio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.servivelog.databinding.FragmentEditarLaboratorioBinding
import com.example.servivelog.domain.model.lab.LabItem
import com.example.servivelog.ui.gestionlaboratorio.viewmodel.GestionLabViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentEditarLaboratorio : Fragment() {
    private lateinit var editarLaboratoriosBinding: FragmentEditarLaboratorioBinding
    private lateinit var labItem: LabItem
    private val args: FragmentEditarLaboratorioArgs by navArgs()
    private val gestionLabViewModel: GestionLabViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        editarLaboratoriosBinding = FragmentEditarLaboratorioBinding.inflate(layoutInflater)
        obteniendoDatos()
        super.onCreate(savedInstanceState)

    }

    private fun obteniendoDatos() {
        labItem = args.lab
        editarLaboratoriosBinding.NombreLabs.setText(labItem.nombre)
        editarLaboratoriosBinding.etDescripcionLabs.setText(labItem.descripcion)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val btnEditar = editarLaboratoriosBinding.btnEditar

        btnEditar.setOnClickListener {
            labItem.nombre = editarLaboratoriosBinding.NombreLabs.text.toString()
            labItem.descripcion = editarLaboratoriosBinding.etDescripcionLabs.text.toString()
            gestionLabViewModel.updateLab(labItem)
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        return editarLaboratoriosBinding.root
    }


}