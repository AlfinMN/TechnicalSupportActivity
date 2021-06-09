package com.projectassyifa.technicalsupportactivities.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.user.model.UserAddModel
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.AddUserViewModel
import kotlinx.android.synthetic.main.activity_add_anggota.*
import kotlinx.android.synthetic.main.list_user.*
import javax.inject.Inject

class AddAnggotaActivity : AppCompatActivity() {

    @Inject
    lateinit var addUserViewModel : AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_anggota)
        (applicationContext as MyApplication).applicationComponent.inject(this)

        addUserViewModel.addUserdata.observe(this,androidx.lifecycle.Observer {
            if (it.status == false) {
                Toast.makeText(
                        this,"Add User Failed", Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Create Account is success", Toast.LENGTH_SHORT).show()
            }
        })

        btn_addUser.setOnClickListener {
            val userAddModel1 = UserAddModel(
                    username = usernameInput.text.toString(),
                    firstname = firstNameInput.text.toString(),
                    lastname = lastNameInput.text.toString(),
                    password = userPasswordInput.text.toString()
            )
            addUserViewModel.addUser(userAddModel1)
            val intent = Intent(this,ListUserActivity::class.java)
            startActivity(intent)
        }
    }
}