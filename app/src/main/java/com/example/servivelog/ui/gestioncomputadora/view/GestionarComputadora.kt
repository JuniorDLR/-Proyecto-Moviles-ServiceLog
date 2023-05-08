package com.example.servivelog.ui.gestioncomputadora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentGestionarComputadoraBinding
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.ui.gestioncomputadora.ComputerAdapter
import com.example.servivelog.ui.gestioncomputadora.viewmodel.GestionCompViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GestionarComputadora : Fragment() {

    private lateinit var gestionarComputadoraBinding: FragmentGestionarComputadoraBinding
    private val gestionCompViewModel: GestionCompViewModel by viewModels()
    private lateinit var addBtn:FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        gestionarComputadoraBinding = FragmentGestionarComputadoraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gestionCompViewModel.onCreate()
        gestionCompViewModel.modeloComputer.observe(viewLifecycleOwner){
            setAdapter(it)
        }
        //it es para mostrar corrutinas , clicklistener , all lo que retornes
        addBtn = gestionarComputadoraBinding.fbtnagregar
        addBtn.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_gestionarComputadora_to_fragmentAgregarComputadora)

        }
        return gestionarComputadoraBinding.root
    }

    private fun setAdapter(it: List<ComputerItem>) {
      recyclerView = gestionarComputadoraBinding.rvComputadora
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = ComputerAdapter(requireActivity(),it,gestionarComputadoraBinding.root,gestionCompViewModel)
    }
}