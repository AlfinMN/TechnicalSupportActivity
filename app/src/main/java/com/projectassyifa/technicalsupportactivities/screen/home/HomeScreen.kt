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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.activity.HomeActivity
import com.projectassyifa.technicalsupportactivities.activity.ReportActivity
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.KegiatanByIdViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import javax.inject.Inject

class HomeScreen : Fragment() {

    var sharedPreferences : SharedPreferences? = null

//    @Inject
//    lateinit var kegiatanByIdViewModel: KegiatanByIdViewModel
//    lateinit var kegiatanByIdAdapter: KegiatanByIdAdapter


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

//        kegiatanRecycleView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
//        kegiatanByIdViewModel.dataById?.observe(viewLifecycleOwner, Observer {
//            kegiatanByIdAdapter = KegiatanByIdAdapter(it,requireActivity())
//            kegiatanRecycleView.adapter = kegiatanByIdAdapter
//            println("INI REVYCLE VIEW KEGIATAN $kegiatanByIdAdapter")
//        })

        val firstname = sharedPreferences?.getString(
            getString(R.string.firstname),
            getString(R.string.default_value)
        )
        val idAkun = sharedPreferences?.getString(
            getString(R.string.id_akun),
            getString(R.string.default_value)
        )
        hiText.text="Hi $firstname"
//        kegiatanByIdViewModel.kegiatanById(idAkun.toString())
        btn_report.setOnClickListener {
            val intent = Intent(getContext(), ReportActivity::class.java)
            startActivity(intent)
        }
    }
}