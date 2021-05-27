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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.activity.UpdateStatus
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.UserByIdViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class KegiatanAllUserAdapter (val listKegiatan : List<AddKegiatanModel>,var activity: Activity) : RecyclerView.Adapter<KegiatanUserViewHolder>(){

    @Inject
    lateinit var userByIdViewModel : UserByIdViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_kegiatan_user,parent,false)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        var lifecycleOwner = parent.context as LifecycleOwner
        return KegiatanUserViewHolder (view,lifecycleOwner)
    }

    override fun onBindViewHolder(holder: KegiatanUserViewHolder , position: Int) {

        var ownerLifecycle = holder.owner

        holder.lokasi.text = listKegiatan[position].lokasi
        holder.tanggal.text = listKegiatan[position].tanggal
        holder.aktivitas.text = listKegiatan[position].aktivitas
        holder.status.text = listKegiatan[position].status
//        holder.nama.text = listKegiatan[position].id_akun
        userByIdViewModel.userById?.observe(ownerLifecycle, Observer {
                    println("INI IT USERNAME ${it[0].username}")
                    holder.nama.text = it[0].username
        })
        userByIdViewModel.userById(listKegiatan[position].id_akun)

        holder.deskripsi.text = listKegiatan[position].remark


        holder.status.setOnClickListener { v->
            if (listKegiatan[position].status == "CLOSE" ){
                println("STATUS SUDAH CLOSE")
                Toast.makeText( v.context,"STATUS SUDAH CLOSE", Toast.LENGTH_SHORT).show()
            } else{
                val bundle= Bundle()
                bundle.putString("id_akun",listKegiatan[position].id_akun)
                bundle.putString("id", listKegiatan[position].id)
                val intent = Intent(v.context, UpdateStatus::class.java)
                intent.putExtras(bundle)
                v.context.startActivity(intent)
                println(listKegiatan[position].id + " " +listKegiatan[position].id_akun)
            }
        }
            val fotoKegiatan = listKegiatan[position].foto_awal
            val fotoProses = listKegiatan[position].foto_proses
            val fotoAkhir = listKegiatan[position].foto_akhir

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
        return listKegiatan.size
    }
}
            class KegiatanUserViewHolder(v: View, lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(v){
                var index : Int = 0
                var nama = v.findViewById<TextView>(R.id.nama)
                var lokasi = v.findViewById<TextView>(R.id.lokasi)
                var tanggal = v.findViewById<TextView>(R.id.tanggal)
                var aktivitas = v.findViewById<TextView>(R.id.aktivitas)
                var status = v.findViewById<TextView>(R.id.status)
                var foto_kegiatan = v.findViewById<ImageView>(R.id.fotoKegiatan)
//                var id = v.findViewById<TextView>(R.id.nama)
                var deskripsi = v.findViewById<TextView>(R.id.deskripsi)
                var fotoProses = v.findViewById<ImageView>(R.id.fotoProses)
                var fotoAkhir = v.findViewById<ImageView>(R.id.fotoAkhir)
                var owner = lifecycleOwner
            }