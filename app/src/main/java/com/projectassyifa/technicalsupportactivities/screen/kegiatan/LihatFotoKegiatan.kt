package com.projectassyifa.technicalsupportactivities.screen.kegiatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectassyifa.technicalsupportactivities.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lihat_foto_kegiatan.*

class LihatFotoKegiatan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_foto_kegiatan)

        var bundle = intent.extras


        var linkFoto = "http://202.62.9.138:1234/rest-api/uploads/krt/${bundle?.getString("namephoto")}"
        Picasso.with(this)
                    .load(linkFoto)
                    .placeholder(R.drawable.img_notfound)
                    .error(R.drawable.img_notfound)
                    .into(lihat_foto)

        x.setOnClickListener {
            onBackPressed()
        }

    }
}