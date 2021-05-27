package com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.activity.InsertKegiatan
import com.projectassyifa.technicalsupportactivities.activity.UpdateStatus
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext


class KegiatanByIdAdapter   ( val kegiatanList :List<AddKegiatanModel>, var activity: Activity
        ) : RecyclerView.Adapter<KegiatanViewHolder>()  {

private lateinit var  fm : FragmentManager
//    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.kegiatan_list_recycle_view,parent,false)
         return KegiatanViewHolder(view)

    }

    override fun onBindViewHolder(holder: KegiatanViewHolder, position: Int) {
        holder.lokasi.text = kegiatanList[position].lokasi
        holder.tanggal.text = kegiatanList[position].tanggal
        holder.aktivitas.text = kegiatanList[position].aktivitas
        holder.status.text = kegiatanList[position].status
//        holder.id.text = kegiatanList[position].id
        holder.deskripsi.text = kegiatanList[position].remark


        holder.status.setOnClickListener { v->
            if (kegiatanList[position].status == "CLOSE" ){
                println("STATUS SUDAH CLOSE")
                Toast.makeText( v.context,"STATUS SUDAH CLOSE", Toast.LENGTH_SHORT).show()
            } else{
                val bundle= Bundle()
                bundle.putString("id_akun",kegiatanList[position].id_akun)
                bundle.putString("id", kegiatanList[position].id)
                val intent = Intent(v.context, UpdateStatus::class.java)
                intent.putExtras(bundle)
                v.context.startActivity(intent)
                println(kegiatanList[position].id + " " +kegiatanList[position].id_akun)
            }
        }
        val fotoKegiatan = kegiatanList[position].foto_awal
        val fotoProses = kegiatanList[position].foto_proses
        val fotoAkhir = kegiatanList[position].foto_akhir

        val urlImage = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoKegiatan"
        val urlImageProses = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoProses"
        val urlImageAkhir = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoAkhir"

    Picasso.with(activity)
            .load(urlImage)
            .placeholder(R.drawable.upload)
            .error(R.drawable.upload)
            .into(holder.foto_kegiatan)
        Picasso.with(activity)
            .load(urlImageProses)
            .placeholder(R.drawable.upload)
            .error(R.drawable.upload)
            .into(holder.fotoProses)
        Picasso.with(activity)
            .load(urlImageAkhir)
            .placeholder(R.drawable.upload)
            .error(R.drawable.upload)
            .into(holder.fotoAkhir)

//        holder.foto_kegiatan.setImageURI(urlImage)
        println("INI URL IMAGE $urlImage")
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
    var status = v.findViewById<TextView>(R.id.status)
    var foto_kegiatan = v.findViewById<ImageView>(R.id.fotoKegiatan)
//    var id = v.findViewById<TextView>(R.id.id)
    var deskripsi = v.findViewById<TextView>(R.id.deskripsi)
    var fotoProses = v.findViewById<ImageView>(R.id.fotoProses)
    var fotoAkhir = v.findViewById<ImageView>(R.id.fotoAkhir)
}

