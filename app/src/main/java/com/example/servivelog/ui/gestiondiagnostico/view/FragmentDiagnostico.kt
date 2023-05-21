package com.example.servivelog.ui.gestiondiagnostico.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R

import com.example.servivelog.databinding.FragmentDiagnosticoBinding
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.diagnosis.DiagnosisItem
import com.example.servivelog.ui.gestiondiagnostico.adapter.DiagnosisAdapter
import com.example.servivelog.ui.gestiondiagnostico.viewmodel.GestioDiagnosisViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FragmentDiagnostico : Fragment(), DiagnosisAdapter.OnDeleteClickListener {
    private lateinit var fragmentDiagnostico: FragmentDiagnosticoBinding
    private lateinit var gestionDiagnosisViewModel: GestioDiagnosisViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DiagnosisAdapter
    private var diagF: List<DiagnosisItem> = emptyList<DiagnosisItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestionDiagnosisViewModel = ViewModelProvider(this)[GestioDiagnosisViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDiagnostico = FragmentDiagnosticoBinding.inflate(inflater, container, false)
        val btnAgregar = fragmentDiagnostico.fbtnagregar

        gestionDiagnosisViewModel.modeloDiagnosis.observe(viewLifecycleOwner) { diagnosis ->
            diagF = diagnosis
            CoroutineScope(Dispatchers.Main).launch {
                val listaC = gestionDiagnosisViewModel.getAllComputer()
                setAdapter(diagF, listaC)
            }
        }

        btnAgregar.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_fragmentDiagnostico_to_fragmentAgregarDiagnostico)
        }

        return fragmentDiagnostico.root
    }

    override fun onDeleteCliked(diagnosisItem: DiagnosisItem) {
        val updateList = adapter.listD.toMutableList()
        updateList.remove(diagnosisItem)
        diagF = updateList
        adapter.updateRecycler(updateList)
    }

    private fun setAdapter(diagF: List<DiagnosisItem>, listaC: List<ComputerItem>) {
        adapter = DiagnosisAdapter(
            requireActivity(),
            requireActivity(),
            diagF,
            fragmentDiagnostico.root,
            gestionDiagnosisViewModel,
            listaC
        )
        adapter.setOnDeleteClickListener(this)
        recyclerView = fragmentDiagnostico.rvDiagnostico
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
    }
}
