package com.example.servivelog.ui.gestionmantenimiento

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R


class MantenimientoAdapter (context: Context): RecyclerView.Adapter<MantenimientoAdapter.MyHolder>(){

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var serviceTag: TextView
        var lab: TextView
        var tipoMan: TextView
        var edit : ImageView
        var delete: ImageView

        init {
            serviceTag = itemView.findViewById(R.id.txtServiceTag)
            lab = itemView.findViewById(R.id.txtLab)
            tipoMan = itemView.findViewById(R.id.txtTipoLimpieza)
            edit = itemView.findViewById(R.id.ivEdit)
            delete = itemView.findViewById(R.id.ivDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }
}