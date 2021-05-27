package com.projectassyifa.technicalsupportactivities.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanAllUserAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanViewModel
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.UserByIdViewModel
import com.projectassyifa.technicalsupportactivities.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_report_all_user.*
import javax.inject.Inject

class ReportAllUser : AppCompatActivity() {

    var sharedPreferences : SharedPreferences? = null




    @Inject
    lateinit var kegiatanViewModel: KegiatanViewModel
    lateinit var kegiatanAllUserAdapter: KegiatanAllUserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_all_user)
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

        kegiatanAllUserRecycleView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        kegiatanViewModel.dataKegiatan?.observe(this,Observer{
            kegiatanAllUserAdapter = KegiatanAllUserAdapter(it,this)
            kegiatanAllUserRecycleView.adapter = kegiatanAllUserAdapter
            println("INI REVYCLE VIEW KEGIATAN ${kegiatanAllUserAdapter}")
        })
        kegiatanViewModel.kegiatanUser()
    }
}