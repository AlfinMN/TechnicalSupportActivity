package com.projectassyifa.technicalsupportactivities.activity

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanByIdViewModel
import kotlinx.android.synthetic.main.activity_report.*
import javax.inject.Inject

class ReportActivity : AppCompatActivity() {

    var sharedPreferences : SharedPreferences? = null
        @Inject
    lateinit var kegiatanByIdViewModel: KegiatanByIdViewModel
    lateinit var kegiatanByIdAdapter: KegiatanByIdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )

                kegiatanRecycleView.layoutManager = LinearLayoutManager(this,
                    RecyclerView.VERTICAL,false)
        kegiatanByIdViewModel.dataById?.observe(this, Observer {
            kegiatanByIdAdapter = KegiatanByIdAdapter(it,this)
            kegiatanRecycleView.adapter = kegiatanByIdAdapter
            println("INI REVYCLE VIEW KEGIATAN $kegiatanByIdAdapter")
        })
        val idAkun = sharedPreferences?.getString(
            getString(R.string.id_akun),
            getString(R.string.default_value)
        )
      kegiatanByIdViewModel.kegiatanById(idAkun.toString())
    }
}