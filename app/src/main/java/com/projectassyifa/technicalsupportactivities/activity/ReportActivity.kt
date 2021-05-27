package com.projectassyifa.technicalsupportactivities.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanByIdViewModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanByPriodeViewModel
import com.projectassyifa.technicalsupportactivities.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_report.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ReportActivity : AppCompatActivity() {

        var sharedPreferences : SharedPreferences? = null
        var cal = Calendar.getInstance()
        var tanggalAwal : String ? = null
        var tanggalAkhir : String ? = null

        @Inject
        lateinit var kegiatanByPriodeViewModel: KegiatanByPriodeViewModel

        @Inject
        lateinit var kegiatanByIdViewModel: KegiatanByIdViewModel
        lateinit var kegiatanByIdAdapter: KegiatanByIdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )

        viewTglAwal.text = "Tanggal Awal"
        viewTglAkhir.text ="Tanggal Akhir"


        //tampil data kegiatan
        kegiatanRecycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
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

        //tampil data kegiatan by range tanggal

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
                println("TANGGAL AWAL $tanggalAwal")
            }
        }
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView2()
                println("TANGGAL AKHIR $tanggalAkhir")
            }
        }

        btn_tglAwal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@ReportActivity,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        btn_tglAkhir.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@ReportActivity,
                        dateSetListener2,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        btn_search.setOnClickListener {
            println("SEARCHING BY TANGGAL")
            kegiatanRecycleView.layoutManager = LinearLayoutManager(this,
                    RecyclerView.VERTICAL,false)
            kegiatanByPriodeViewModel.dataByPriode?.observe(this, Observer {
                kegiatanByIdAdapter = KegiatanByIdAdapter(it,this)
                kegiatanRecycleView.adapter = kegiatanByIdAdapter
                println("INI REVYCLE VIEW KEGIATAN $kegiatanByIdAdapter")
            })
            val idAkun = sharedPreferences?.getString(
                    getString(R.string.id_akun),
                    getString(R.string.default_value)
            )
            val tglAwal = tanggalAwal
            val tglAkhir = tanggalAkhir
            kegiatanByPriodeViewModel.kegiatanByPriode(idAkun.toString(),tglAwal.toString(),tglAkhir.toString())
        }


    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        viewTglAwal.text = sdf.format(cal.getTime())
        tanggalAwal = sdf.format(cal.getTime())


    }
    private fun updateDateInView2() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        viewTglAkhir.text = sdf.format(cal.getTime())
        tanggalAkhir = sdf.format(cal.getTime())

    }
}