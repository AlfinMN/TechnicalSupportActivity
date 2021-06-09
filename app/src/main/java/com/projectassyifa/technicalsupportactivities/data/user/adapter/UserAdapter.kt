package com.projectassyifa.technicalsupportactivities.data.user.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.data.user.viewmodel.GetUserViewModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.http.GET
import javax.inject.Inject

class UserAdapter (val listUser : List<UserModel>,var activity: Activity)
    : RecyclerView.Adapter<UserViewHolder>(){

        @Inject
        lateinit var getUserViewModel: GetUserViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user,parent,false)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        var lifecycleOwner = parent.context as LifecycleOwner

        return UserViewHolder(view,lifecycleOwner)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.id.text = listUser[position].id_akun
        holder.username.text = listUser[position].username
        holder.firstname.text = listUser[position].firstname
        holder.lastname.text = listUser[position].lastname
        holder.jabatan.text = listUser[position].akun_level
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


}

class UserViewHolder(v: View, lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder(v) {


    var id = v.findViewById<TextView>(R.id.id)
    var username = v.findViewById<TextView>(R.id.username)
    var firstname = v.findViewById<TextView>(R.id.firstname)
    var lastname = v.findViewById<TextView>(R.id.lastname)
    var jabatan = v.findViewById<TextView>(R.id.jabatan)

}
