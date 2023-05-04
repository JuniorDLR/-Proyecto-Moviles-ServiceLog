package com.example.servivelog.ui.gestionmantenimiento.view

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
import com.example.servivelog.databinding.FragmentGestionMantenimientoBinding
import com.example.servivelog.domain.model.MantenimientoItem
import com.example.servivelog.ui.gestionmantenimiento.MantenimientoAdapter
import com.example.servivelog.ui.gestionmantenimiento.viewmodel.GestionManteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentGestionMantenimiento : Fragment() {

    private val gestionManteViewModel: GestionManteViewModel by viewModels()
    private lateinit var gestionMantenimientoBinding: FragmentGestionMantenimientoBinding
    private lateinit var addBtn: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        gestionMantenimientoBinding = FragmentGestionMantenimientoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        gestionManteViewModel.onCreate()

        gestionManteViewModel.modeloMantenimiento.observe(viewLifecycleOwner){
            setAdapter(it)
        }

        addBtn = gestionMantenimientoBinding.fbtnagregar

        addBtn.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_fragmentGestionMantenimiento_to_fragmentAgregarMantenimiento)
        }

        return gestionMantenimientoBinding.root
    }

    private fun setAdapter(it: List<MantenimientoItem>) {
        recyclerView = gestionMantenimientoBinding.rvMantenimiento
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = MantenimientoAdapter(requireActivity(), it, gestionMantenimientoBinding.root)
    }
}