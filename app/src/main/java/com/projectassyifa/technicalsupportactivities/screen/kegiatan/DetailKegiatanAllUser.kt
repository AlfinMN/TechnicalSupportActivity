package com.projectassyifa.technicalsupportactivities.screen.kegiatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectassyifa.technicalsupportactivities.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.activity_detail_kegiatan.aktivitas
import kotlinx.android.synthetic.main.activity_detail_kegiatan.foto_akhir
import kotlinx.android.synthetic.main.activity_detail_kegiatan.foto_awal
import kotlinx.android.synthetic.main.activity_detail_kegiatan.foto_proses
import kotlinx.android.synthetic.main.activity_detail_kegiatan.id
import kotlinx.android.synthetic.main.activity_detail_kegiatan.status
import kotlinx.android.synthetic.main.activity_detail_kegiatan.tanggal
import kotlinx.android.synthetic.main.activity_detail_kegiatan_all_user.*

class DetailKegiatanAllUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan_all_user)

        var bundle = intent.extras

        var linkFotoAwal = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_awal")}"
        var linkFotoProses = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_proses")}"
        var linkFotoAkhir = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_akhir")}"

        id.text = ": ${bundle?.getString("id")}"
        aktivitas.text = ": ${bundle?.getString("aktivitas")}"
        tanggal.text = ": ${bundle?.getString("tanggal")}"
        status.text = ": ${bundle?.getString("status")}"
        nama_pegawai.text = ": ${bundle?.getString("nama_pegawai")}"
        Picasso.with(this)
            .load(linkFotoAwal)
            .placeholder(R.drawable.img_notfound)
            .error(R.drawable.img_notfound)
            .into(foto_awal)

        Picasso.with(this)
            .load(linkFotoProses)
            .placeholder(R.drawable.img_notfound)
            .error(R.drawable.img_notfound)
            .into(foto_proses)

        Picasso.with(this)
            .load(linkFotoAkhir)
            .placeholder(R.drawable.img_notfound)
            .error(R.drawable.img_notfound)
            .into(foto_akhir)
    }
}