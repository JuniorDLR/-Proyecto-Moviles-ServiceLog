package com.example.servivelog.ui.gestiondiagnostico.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentEditarDiagnosticoBinding
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.servivelog.domain.model.diagnosis.DiagnosisItem
import com.example.servivelog.ui.gestiondiagnostico.viewmodel.GestioDiagnosisViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentEditarDiagnostico : Fragment() {
    private lateinit var editarDiagnosticoBinding: FragmentEditarDiagnosticoBinding
    private lateinit var diagnosisItem: DiagnosisItem
    private val args: FragmentEditarDiagnosticoArgs by navArgs()
    private val gestionDiagnosisViewModel: GestioDiagnosisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editarDiagnosticoBinding = FragmentEditarDiagnosticoBinding.inflate(layoutInflater)
        obteniendoDatos()
    }

    private fun obteniendoDatos() {
        diagnosisItem = args.diagnosis
        editarDiagnosticoBinding.etLabs.setText(diagnosisItem.nombrelab)
        editarDiagnosticoBinding.etServiPc.setText(diagnosisItem.ServiceTag)
        editarDiagnosticoBinding.etDescripcionDiagnostico.setText(diagnosisItem.descripcion)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val btnEditar = editarDiagnosticoBinding.btnEditar

        btnEditar.setOnClickListener {
            diagnosisItem.nombrelab = editarDiagnosticoBinding.etLabs.text.toString()
            diagnosisItem.ServiceTag = editarDiagnosticoBinding.etServiPc.text.toString()
            diagnosisItem.descripcion = editarDiagnosticoBinding.etDescripcionDiagnostico.text.toString()

            gestionDiagnosisViewModel.updateDiagnosis(diagnosisItem)
            Toast.makeText(requireContext(), "Diagnóstico editado correctamente", Toast.LENGTH_SHORT).show()
            val navController = Navigation.findNavController(requireView())
            navController.popBackStack()
        }

        return editarDiagnosticoBinding.root
    }
}
