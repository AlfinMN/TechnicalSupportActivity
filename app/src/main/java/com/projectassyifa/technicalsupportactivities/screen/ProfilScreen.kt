package com.projectassyifa.technicalsupportactivities.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.projectassyifa.technicalsupportactivities.MainActivity
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.activity.LoginActivity
import com.projectassyifa.technicalsupportactivities.activity.ReportActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profil_screen.*


class ProfilScreen : Fragment(),View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstname = sharedPreferences?.getString(
            getString(R.string.firstname),
            getString(R.string.default_value)
        )
        val lastname = sharedPreferences?.getString(
            getString(R.string.lastname),
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
        val unit = sharedPreferences?.getString(
                getString(R.string.unit),
                getString(R.string.default_value)
        )
        namauser.text = "$namaPegawai"
        jabatan.text = "$unit"

        logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            logout -> {
                println("klik logout")
                with(sharedPreferences?.edit()) {
                    this?.clear()
                    this?.apply()
                    Intent(getContext(), LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.also {  startActivity(it) }
//                    v?.findNavController()
//                        ?.navigate(R.id.action_homeScreen_to_mainActivity2)
                }
            }
        }
    }
}