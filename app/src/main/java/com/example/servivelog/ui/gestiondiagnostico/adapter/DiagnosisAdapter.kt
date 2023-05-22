package com.example.servivelog.ui.gestiondiagnostico.adapter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.diagnosis.DiagnosisItem
import com.example.servivelog.domain.model.user.ActiveUser
import com.example.servivelog.ui.gestiondiagnostico.view.FragmentDiagnosticoDirections
import com.example.servivelog.ui.gestiondiagnostico.viewmodel.GestioDiagnosisViewModel
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Image
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiagnosisAdapter(
    private val activity: Activity,
    var context: Context,
    var listD: List<DiagnosisItem>,
    var view: View,
    var gestioDiagnosisViewModel: GestioDiagnosisViewModel,
    val listaC: List<ComputerItem>
) : RecyclerView.Adapter<DiagnosisAdapter.MyHolder>(), Filterable {

    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1002
    private var originalList: List<DiagnosisItem> = listD
    private var filteredList: List<DiagnosisItem> = listD

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

    override fun getItemCount(): Int {
        return filteredList.size
    }

    interface OnDeleteClickListener {
        fun onDeleteCliked(diagnosisItem: DiagnosisItem)
    }

    private var onDeleteClickListener: OnDeleteClickListener? = null

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val diag = filteredList[position]
        holder.serviceTag.text = diag.ServiceTag
        holder.descripcion.text = diag.descripcion

        holder.edit.setOnClickListener {
            if (diag.ServiceTag == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacía", Toast.LENGTH_SHORT)
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
                Toast.makeText(context, "La base de datos se encuentra vacía", Toast.LENGTH_SHORT)
                    .show()
            } else {
                gestioDiagnosisViewModel.deleteDiagnosis(diag)
            }
        }

        holder.logo.setOnClickListener {
            if (diag.ServiceTag == "Sin datos") {
                Toast.makeText(context, "La base de datos se encuentra vacía", Toast.LENGTH_SHORT)
                    .show()
            } else {
                generarReporteDiagnosi(diag)
            }
        }
    }


    fun updateRecycler(updateList: MutableList<DiagnosisItem>) {
        listD = updateList
        originalList = updateList
        filteredList = updateList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim() ?: ""
                filteredList = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { item ->
                        item.ServiceTag.contains(query, ignoreCase = true)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredList = results?.values as? List<DiagnosisItem> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }

    private fun generarReporteDiagnosi(diag: DiagnosisItem) {
        // Lógica para generar el reporte de diagnóstico

        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Si dan que no se piden otra vez
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

            val carpeta = "/diagnosticoPDF"
            val path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + carpeta
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
                Toast.makeText(context, "Se ha creado el directorio", Toast.LENGTH_SHORT).show()
            }
            val file =
                File(
                    dir,
                    "${diag.ServiceTag}_${diag.nombrelab}_${currentDate}_${formattedTime}.pdf"
                )
            val uri =
                FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            val fos = FileOutputStream(file)

            val computadora = listaC.find { it.serviceTag == diag.nombrelab }

            val document = Document()
            PdfWriter.getInstance(document, fos)
            // Abrir el documento para escribir
            document.open()

            val titulo = Paragraph("REPORTE TECNICO")
            titulo.alignment = Element.ALIGN_CENTER
            titulo.font.size = 18f
            titulo.spacingAfter = 10f
            titulo.font.setStyle("bold")
            document.add(titulo)

            document.add(Paragraph(" "))

            var tabla = PdfPTable(2)
            tabla.widthPercentage = 100f
            tabla.setWidths(floatArrayOf(45f, 75f))
            tabla.horizontalAlignment = Element.ALIGN_LEFT

            var celda = PdfPCell(Phrase("Usuario: "))
            celda.border = Rectangle.NO_BORDER
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda)

            var celda2 = PdfPCell(Phrase(ActiveUser.userName))
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda2)

            document.add(tabla)

            document.add(Paragraph(" "))

            tabla = PdfPTable(2)
            tabla.widthPercentage = 100f
            tabla.setWidths(floatArrayOf(45f, 75f))
            tabla.horizontalAlignment = Element.ALIGN_LEFT

            celda = PdfPCell(Phrase("Laboratorio: "))
            celda.border = Rectangle.NO_BORDER
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda)

            celda2 = PdfPCell(Phrase(diag.nombrelab))
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda2)

            document.add(tabla)

            document.add(Paragraph(" "))

            tabla = PdfPTable(2)
            tabla.widthPercentage = 100f
            tabla.setWidths(floatArrayOf(45f, 75f))
            tabla.horizontalAlignment = Element.ALIGN_LEFT

            celda = PdfPCell(Phrase("Códigos de la computadora: "))
            celda.border = Rectangle.NO_BORDER
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda)

            celda2 = PdfPCell(Phrase("${diag.ServiceTag} / ${computadora?.noInventario}"))
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda2)

            document.add(tabla)

            document.add(Paragraph(" "))
            val descripcion = Paragraph("Descripción: ")
            document.add(descripcion)

            document.add(Paragraph(" "))

            tabla = PdfPTable(1)
            tabla.horizontalAlignment = Element.ALIGN_LEFT

            celda2 = PdfPCell(Phrase(diag.descripcion))
            celda.horizontalAlignment = Element.ALIGN_LEFT
            tabla.addCell(celda2)

            document.add(tabla)

            val documentacion = Paragraph("Documentacion:")
            document.add(documentacion)

            var imagen = Image.getInstance(diag.ruta1)
            if (diag.ruta1 != "") {
                imagen.alignment = Element.ALIGN_CENTER
                document.add(imagen)
            }
            if (diag.ruta2 != "") {
                imagen = Image.getInstance(diag.ruta2)
                imagen.alignment = Element.ALIGN_CENTER
                document.add(imagen)
            }
            if (diag.ruta3 != "") {
                imagen = Image.getInstance(diag.ruta3)
                imagen.alignment = Element.ALIGN_CENTER
                document.add(imagen)
            }
            if (diag.ruta4 != "") {
                imagen = Image.getInstance(diag.ruta4)
                imagen.alignment = Element.ALIGN_CENTER
                document.add(imagen)
            }


            document.close()


            val pdfViewIntent = Intent(Intent.ACTION_VIEW)
            pdfViewIntent.setDataAndType(uri, "application/pdf")
            pdfViewIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                context.startActivity(pdfViewIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No hay aplicación para ver archivos PDF instalada",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Toast.makeText(context, "Reporte generado correctamente", Toast.LENGTH_SHORT)
                .show()

        }
    }

}
