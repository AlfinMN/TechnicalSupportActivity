package com.projectassyifa.technicalsupportactivities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)
        bottomNavView.background = null
        bottomNavView.menu.getItem(1).isEnabled =false
        fab.setOnClickListener {
            val intent = Intent(this,InsertKegiatan::class.java)
            startActivity(intent)
        }

        navController = (nav_main_host_fragment_container as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavView,navController)
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.report -> {
                    navController.navigate(R.id.action_global_to_homeScreen)
                    true
                }
                R.id.profil -> {
                    navController.navigate(R.id.action_homeScreen_to_profilScreen)
                    true
                }
               else -> {
                true
            }
            }
        }
    }
}