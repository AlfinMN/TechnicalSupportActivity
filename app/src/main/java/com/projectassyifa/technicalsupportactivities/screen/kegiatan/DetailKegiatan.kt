package com.projectassyifa.technicalsupportactivities.screen.kegiatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectassyifa.technicalsupportactivities.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*

class DetailKegiatan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        var bundle = intent.extras

        var linkFotoAwal = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_awal")}"
        var linkFotoProses = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_proses")}"
        var linkFotoAkhir = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("foto_akhir")}"

        id.text = ": ${bundle?.getString("id")}"
        aktivitas.text = ": ${bundle?.getString("aktivitas")}"
       tanggal.text = ": ${bundle?.getString("tanggal")}"
        status.text = ": ${bundle?.getString("status")}"

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


