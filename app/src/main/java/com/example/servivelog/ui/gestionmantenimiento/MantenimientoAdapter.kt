package com.example.servivelog.ui.gestionmantenimiento

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.domain.model.MantenimientoItem


class MantenimientoAdapter (var context: Context,
                            var listM: List<MantenimientoItem>,
                            var view: View): RecyclerView.Adapter<MantenimientoAdapter.MyHolder>(){

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
        val itemView = LayoutInflater.from(context).inflate(R.layout.maintenance_item, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listM.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val mant =listM[position]
        holder.serviceTag.text = mant.computadora
        holder.lab.text = mant.labname
        holder.tipoMan.text = mant.tipoLimpieza
    }
}