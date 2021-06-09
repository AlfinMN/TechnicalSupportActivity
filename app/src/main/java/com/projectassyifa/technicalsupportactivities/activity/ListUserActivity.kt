package com.projectassyifa.technicalsupportactivities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.user.adapter.UserAdapter
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.GetUserViewModel
import com.projectassyifa.technicalsupportactivities.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_list_user.*
import javax.inject.Inject

class ListUserActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel : GetUserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)
        (applicationContext as MyApplication).applicationComponent.inject(this)

        allUserRecycleView.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL,false)
        userViewModel.dataUser?.observe(this, Observer {
            userAdapter = UserAdapter(it,this)
            allUserRecycleView.adapter = userAdapter
        })
        userViewModel.getUser()

        btn_addUser.setOnClickListener {
            val intent = Intent(this,AddAnggotaActivity::class.java)
            startActivity(intent)
        }
    }
}