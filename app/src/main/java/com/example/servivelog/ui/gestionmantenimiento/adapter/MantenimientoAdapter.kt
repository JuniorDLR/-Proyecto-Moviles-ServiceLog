package com.example.servivelog.ui.gestionmantenimiento.adapter

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.domain.model.user.ActiveUser
import com.example.servivelog.ui.gestionmantenimiento.view.FragmentGestionMantenimientoDirections
import com.example.servivelog.ui.gestionmantenimiento.viewmodel.GestionManteViewModel
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MantenimientoAdapter(
    private val activity: Activity,
    var context: Context,
    var listM: List<MantenimientoCUDItem>,
    var view: View,
    var gestionManteViewModel: GestionManteViewModel,
    val listaC: List<ComputerItem>
) : RecyclerView.Adapter<MantenimientoAdapter.MyHolder>() {

    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1001

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var serviceTag: TextView
        var lab: TextView
        var tipoMan: TextView
        var edit: ImageView
        var delete: ImageView
        var logo: ImageView

        init {
            serviceTag = itemView.findViewById(R.id.txtServiceTag)
            lab = itemView.findViewById(R.id.txtLab)
            tipoMan = itemView.findViewById(R.id.txtTipoLimpieza)
            edit = itemView.findViewById(R.id.ivEdit)
            delete = itemView.findViewById(R.id.ivDelete)
            logo = itemView.findViewById(R.id.iconoCvComputadora)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.maintenance_item, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listM.size
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val mant = listM[position]
        holder.serviceTag.text = mant.computadora
        holder.lab.text = mant.labname
        holder.tipoMan.text = mant.tipoLimpieza

        holder.edit.setOnClickListener {
            if (mant.computadora == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacia", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action =
                    FragmentGestionMantenimientoDirections.actionFragmentGestionMantenimientoToFragmentEditMantenimiento(
                        mant
                    )
                Navigation.findNavController(view).navigate(action)
            }
        }

        holder.delete.setOnClickListener {
            if (mant.computadora == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacia", Toast.LENGTH_SHORT)
                    .show()
            } else {
                gestionManteViewModel.deleteMantenimiento(mant)
            }
        }

        holder.logo.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted, request for the permission
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                val currentTime = Date()
                val timeFormat = SimpleDateFormat("HH_mm_ss", Locale.getDefault())
                val formattedTime = timeFormat.format(currentTime)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val currentDate = sdf.format(Date())

                val carpeta = "/archivosPDF"
                val path =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + carpeta
                val dir = File(path)
                if (!dir.exists()) {
                    dir.mkdirs()
                    Toast.makeText(context, "Se ha creado el directorio", Toast.LENGTH_SHORT).show()
                }
                val file = File(dir, "${mant.computadora}_${currentDate}_${formattedTime}.pdf")
                val fos = FileOutputStream(file)

                val computadora = listaC.find { it.serviceTag == mant.computadora }

                val document = Document()
                PdfWriter.getInstance(document, fos)
                // Abrir el documento para escribir
                document.open()


                val titulo = Paragraph("REPORTE DE MANTENIMIENTO")
                titulo.alignment = Element.ALIGN_CENTER
                titulo.font.size = 18f
                titulo.spacingAfter = 10f
                titulo.font.setStyle("bold")
                document.add(titulo)

                document.add(Paragraph(" "))
                val txtUsuario = Paragraph("Usuario: ${ActiveUser.userName}")
                txtUsuario.alignment = Element.ALIGN_LEFT
                document.add(txtUsuario)

                document.add(Paragraph(" "))
                val txtLab = Paragraph("Laboratorio: ${mant.labname}")
                txtLab.alignment = Element.ALIGN_LEFT
                document.add(txtLab)

                document.add(Paragraph(" "))
                val txtCodigos =
                    Paragraph("Códigos de la computadora: ${mant.computadora} / ${computadora?.noInventario}")
                txtCodigos.alignment = Element.ALIGN_LEFT
                document.add(txtCodigos)

                document.add(Paragraph(" "))
                val tipoMantenimiento = Paragraph("Tipo de mantenimiento: ${mant.tipoLimpieza}")
                tipoMantenimiento.alignment = Element.ALIGN_LEFT
                document.add(tipoMantenimiento)

                document.add(Paragraph(" "))
                val descripcion = Paragraph("Descripción: ${mant.desc}")
                document.add(descripcion)

                document.close()

                Toast.makeText(context, "Reporte generado correctamente", Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
}