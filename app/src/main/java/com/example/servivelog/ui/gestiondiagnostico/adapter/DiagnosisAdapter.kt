package com.example.servivelog.ui.gestiondiagnostico.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.diagnosis.DiagnosisItem
import com.example.servivelog.ui.gestiondiagnostico.view.FragmentDiagnostico
import com.example.servivelog.ui.gestiondiagnostico.view.FragmentDiagnosticoDirections
import com.example.servivelog.ui.gestiondiagnostico.viewmodel.GestioDiagnosisViewModel

class DiagnosisAdapter(
    private val activity: Activity,
    var context: Context,
    var listD: List<DiagnosisItem>,
    var view: View,
    var gestioDiagnosisViewModel: GestioDiagnosisViewModel,
    val listaC: List<ComputerItem>
) : RecyclerView.Adapter<DiagnosisAdapter.MyHolder>() {
    private val MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1001

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var serviceTag: TextView
        var descripcion: TextView
        var edit: ImageView
        var delete: ImageView
        var logo: ImageView

        init {

            serviceTag = itemView.findViewById(R.id.txtServiceTag)
            descripcion = itemView.findViewById(R.id.txtDescripcion)
            edit = itemView.findViewById(R.id.ivEdit)
            delete = itemView.findViewById(R.id.ivDelete)
            logo = itemView.findViewById(R.id.iconoCvComputadora)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.diagnosis_item, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount() = listD.size

    interface OnDeleteClickListener {
        fun onDeleteCliked(diagnosisItem: DiagnosisItem)
    }

    private var onDeleteClickListener: OnDeleteClickListener? = null

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val diag = listD[position]
        holder.serviceTag.text = diag.ServiceTag
        holder.descripcion.text = diag.descripcion


        holder.edit.setOnClickListener {
            if (diag.ServiceTag == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacia", Toast.LENGTH_SHORT)
                    .show()

            } else {
                val action =
                    FragmentDiagnosticoDirections.actionFragmentDiagnosticoToFragmentEditarDiagnostico(
                        diag
                    )
                Navigation.findNavController(view).navigate(action)
            }

        }
        holder.delete.setOnClickListener {
            if (diag.ServiceTag == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacia", Toast.LENGTH_SHORT)
                    .show()

            } else {
                gestioDiagnosisViewModel.deleteDiagnosis(diag)
            }
        }

        holder.logo.setOnClickListener {
            if (diag.ServiceTag == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacia", Toast.LENGTH_SHORT)
                    .show()
            } else {
                generarReporteDiagnosi(diag)
            }


        }
    }

    private fun generarReporteDiagnosi(diag: DiagnosisItem) {

    }

    fun updateRecycler(updateList: MutableList<DiagnosisItem>) {
        listD = updateList
        notifyDataSetChanged()

    }
}