package com.projectassyifa.technicalsupportactivities.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.technicalsupportactivities.data.user.api.AddUserAPI
import com.projectassyifa.technicalsupportactivities.data.user.model.UserAddModel
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AddUserRepo @Inject constructor(val addUserAPI: AddUserAPI){
    var addUser = MutableLiveData<KerangkaResponse>()

    fun AddUser(userAddModel: UserAddModel){
        addUserAPI.addUser(userAddModel).enqueue(object : Callback<KerangkaResponse>{
            override fun onResponse(call: Call<KerangkaResponse>, response: Response<KerangkaResponse>) {
                val res = response?.body()
                var status = res?.status
                val stringResponse = Gson().toJson(res)
                val userRegisterResponseObject =
                        Gson().fromJson<KerangkaResponse>(stringResponse, KerangkaResponse::class.java)
               addUser.value = userRegisterResponseObject
//                if ( status == false) {
//                   var message = res?.message
//                    println("$message")
//                } else {
//                    var message = res?.message
//                    println("$message")
//                }
            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}

