package com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.UserByIdViewModel
import com.projectassyifa.technicalsupportactivities.screen.kegiatan.DetailKegiatan
import com.projectassyifa.technicalsupportactivities.screen.kegiatan.DetailKegiatanAllUser
import com.projectassyifa.technicalsupportactivities.screen.kegiatan.LihatFotoKegiatan
import com.squareup.picasso.Picasso
import javax.inject.Inject

class KegiatanAllUserAdapter (val listKegiatan : List<AddKegiatanModel>,var activity: Activity) : RecyclerView.Adapter<KegiatanUserViewHolder>(){


//    var itemView : View? = null
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

//        holder.lokasi.text = listKegiatan[position].lokasi
        holder.tanggal.text = listKegiatan[position].tanggal
        holder.aktivitas.text = listKegiatan[position].aktivitas
        holder.nama.text = listKegiatan[position].nama_pegawai
        holder.details.setOnClickListener { 
            val bundle = Bundle()

            bundle.putString("id",listKegiatan[position].id)
            bundle.putString("nama_pegawai",listKegiatan[position].nama_pegawai)
            bundle.putString("aktivitas",listKegiatan[position].aktivitas)
            bundle.putString("tanggal",listKegiatan[position].tanggal)
            bundle.putString("status",listKegiatan[position].status)
            bundle.putString("foto_awal",listKegiatan[position].foto_awal)
            bundle.putString("foto_proses",listKegiatan[position].foto_proses)
            bundle.putString("foto_akhir",listKegiatan[position].foto_akhir)

            val  move = Intent(activity, DetailKegiatanAllUser::class.java)
            move.putExtras(bundle)
            activity.startActivity(move)
        }
//        holder.status.text = listKegiatan[position].status
//        holder.nama.text = listKegiatan[position].id_akun
//        userByIdViewModel.userById?.observe(ownerLifecycle, Observer {
//                    println("INI IT USERNAME ${it[0].username}")
//                    holder.nama.text = it[0].username
//        })
        userByIdViewModel.userById(listKegiatan[position].id_akun)
//        holder.deskripsi.text = listKegiatan[position].remark

            val fotoKegiatan = listKegiatan[position].foto_awal
            val fotoProses = listKegiatan[position].foto_proses
            val fotoAkhir = listKegiatan[position].foto_akhir

            val urlImage = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoKegiatan"
            val urlImageProses = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoProses"
            val urlImageAkhir = "http://202.62.9.138:1234/rest-api/uploads/krt/$fotoAkhir"

//        holder.foto_kegiatan.setOnClickListener { v->
//            println("KLIK FOTO KEGIATAN ${listKegiatan[position].foto_awal}")
//             val bundle = Bundle()
//           bundle.putString("namephoto",listKegiatan[position].foto_awal)
//            val intent = Intent(activity,LihatFotoKegiatan::class.java)
//            intent.putExtras(bundle)
//            activity.startActivity(intent)
//        }

//        holder.fotoProses.setOnClickListener { v->
//            val bundle = Bundle()
//            bundle.putString("namephoto",listKegiatan[position].foto_proses)
//            val intent = Intent(activity,LihatFotoKegiatan::class.java)
//            intent.putExtras(bundle)
//            activity.startActivity(intent)
//        }
//
//        holder.fotoAkhir.setOnClickListener { v->
//            val bundle = Bundle()
//            bundle.putString("namephoto",listKegiatan[position].foto_akhir)
//            val intent = Intent(activity,LihatFotoKegiatan::class.java)
//            intent.putExtras(bundle)
//            activity.startActivity(intent)
//        }

//            Picasso.with(activity)
//                    .load(urlImage)
//                    .placeholder(R.drawable.img_notfound)
//                    .error(R.drawable.img_notfound)
//                    .into(holder.foto_kegiatan)
//            Picasso.with(activity)
//                    .load(urlImageProses)
//                    .placeholder(R.drawable.img_notfound)
//                    .error(R.drawable.img_notfound)
//                    .into(holder.fotoProses)
//            Picasso.with(activity)
//                    .load(urlImageAkhir)
//                    .placeholder(R.drawable.img_notfound)
//                    .error(R.drawable.img_notfound)
//                    .into(holder.fotoAkhir)

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
//                var lokasi = v.findViewById<TextView>(R.id.lokasi)
                var tanggal = v.findViewById<TextView>(R.id.tanggal)
                var aktivitas = v.findViewById<TextView>(R.id.aktivitas)
                var details = v.findViewById<Button>(R.id.btn_detailUser)
//                var status = v.findViewById<TextView>(R.id.status)
//                var foto_kegiatan = v.findViewById<ImageView>(R.id.fotoKegiatan)
//                var deskripsi = v.findViewById<TextView>(R.id.deskripsi)
//                var fotoProses = v.findViewById<ImageView>(R.id.fotoProses)
//                var fotoAkhir = v.findViewById<ImageView>(R.id.fotoAkhir)
                var owner = lifecycleOwner

            }

