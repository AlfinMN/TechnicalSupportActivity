package com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter



import android.app.Activity
import android.view.LayoutInflater
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_insert_kegiatan.*
import java.io.File
import kotlin.coroutines.coroutineContext


class KegiatanByIdAdapter   ( val kegiatanList :List<AddKegiatanModel>, var activity: Activity
        ) : RecyclerView.Adapter<KegiatanViewHolder>()  {


//    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.kegiatan_list_recycle_view,parent,false)
         return KegiatanViewHolder(view)
    }

    override fun onBindViewHolder(holder: KegiatanViewHolder, position: Int) {
        holder.lokasi.text = kegiatanList[position].lokasi
        holder.tanggal.text = kegiatanList[position].tanggal
        holder.aktivitas.text = kegiatanList[position].aktivitas
        val fotoKegiatan = kegiatanList[position].foto_awal
        val urlImage = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoKegiatan"

    Picasso.with(activity)
            .load(urlImage)
            .placeholder(R.drawable.upload)
            .error(R.drawable.upload)
            .into(holder.foto_kegiatan)

//        holder.foto_kegiatan.setImageURI(urlImage)
        println(urlImage)
    }

    override fun getItemCount(): Int {
        return kegiatanList.size
    }

}

class KegiatanViewHolder (v: View) : RecyclerView.ViewHolder(v){
    var index : Int = 0
    var lokasi = v.findViewById<TextView>(R.id.lokasi)
    var tanggal = v.findViewById<TextView>(R.id.tanggal)
    var aktivitas = v.findViewById<TextView>(R.id.aktivitas)
    var foto_kegiatan = v.findViewById<ImageView>(R.id.fotoKegiatan)
}
