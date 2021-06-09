package com.projectassyifa.technicalsupportactivities.screen.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.activity.*
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanByIdViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import javax.inject.Inject

class HomeScreen : Fragment() {

    var sharedPreferences : SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstname = sharedPreferences?.getString(
            getString(R.string.firstname),
            getString(R.string.default_value)
        )
        val idPegawai = sharedPreferences?.getString(
            getString(R.string.id_pegawai),
            getString(R.string.default_value)
        )
        val akun_level = sharedPreferences?.getString(
                getString(R.string.akun_level),
                getString(R.string.default_value)
        )
        val namaPegawai = sharedPreferences?.getString(
                getString(R.string.nama_pegawai),
                getString(R.string.default_value)
        )

        if (idPegawai== "1532") {
            btn_reportUser.isVisible = true
            reportUserText.isVisible = true

        } else {
            btn_reportUser.isVisible = false
            reportUserText.isVisible = false
        }

        hiText.text="Hi, $namaPegawai"
        btn_report.setOnClickListener {
            val intent = Intent(getContext(), ReportActivity::class.java)
            startActivity(intent)
        }
        btn_reportUser.setOnClickListener {
            val intent = Intent(getContext(), ReportAllUser::class.java)
            startActivity(intent)
        }
//        btn_addtask.setOnClickListener {
//            val intent = Intent(getContext(), AddJob::class.java)
//            startActivity(intent)
//        }
//        btn_member.setOnClickListener {
//            val intent = Intent(getContext(), ListUserActivity::class.java)
//            startActivity(intent)
//        }
    }
}