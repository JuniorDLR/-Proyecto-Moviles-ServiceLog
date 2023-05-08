package com.example.servivelog.ui.gestionlaboratorio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentLaboratoriosBinding
import com.example.servivelog.domain.model.lab.LabItem
import com.example.servivelog.ui.gestionlaboratorio.LabAdapter
import com.example.servivelog.ui.gestionlaboratorio.viewmodel.GestionLabViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentLaboratorios : Fragment() {

    private lateinit var laboratorioBinding: FragmentLaboratoriosBinding

    private val gestionLabViewModel: GestionLabViewModel by viewModels()
    private lateinit var addbtn: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        laboratorioBinding = FragmentLaboratoriosBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addbtn = laboratorioBinding.fbtnAdd

        gestionLabViewModel.onCreate()
        gestionLabViewModel.modeloLab.observe(viewLifecycleOwner){
            setAdapter(it)
        }

        addbtn.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_Fragmentlaboratorios_to_fragmentAgregarLaboratorio)
        }

        return laboratorioBinding.root
    }

    private fun setAdapter(it: List<LabItem>){
        recyclerView = laboratorioBinding.rvLab
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = LabAdapter(requireActivity(), it, laboratorioBinding.root, gestionLabViewModel)
    }

}