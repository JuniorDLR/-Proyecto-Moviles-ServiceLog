package com.example.servivelog.ui.usuario.adapterMantenimiento

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem


class DashboardM(
    private val activity: Activity,
    var context: Context,
    var list: List<MantenimientoCUDItem>,
    var view: View,
    var listac: List<ComputerItem>
) : RecyclerView.Adapter<DashboardM.MyHolder>() {
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var serviceTagM: TextView
        var tipoMant: TextView
        var logo: ImageView

        init {
            serviceTagM = itemView.findViewById(R.id.txtServiceTagM)
            tipoMant = itemView.findViewById(R.id.txtTipoLimpieza)
            logo = itemView.findViewById(R.id.iconoCvComputadora)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder { //manda a llamar los cards sin linkear a la raiz
        val itemView = LayoutInflater.from(context).inflate(R.layout.dashboard_mitem, parent, false)//hace que la clase my holderempieza a enviar el itemview
        return MyHolder(itemView)
    }

    override fun getItemCount() = list.size //obtiene cantidad obj en el recycler


    override fun onBindViewHolder(holder: MyHolder, position: Int) { //obtiene la posicion

        val mant = list[position]
        holder.serviceTagM.text = mant.computadora
        holder.tipoMant.text = mant.computadora

    }
}