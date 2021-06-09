package com.projectassyifa.technicalsupportactivities.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.technicalsupportactivities.data.user.api.GetUserAPI
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class GetUserRepo @Inject constructor(val getUserAPI : GetUserAPI){
    var dataUser : MutableLiveData<List<UserModel>> = MutableLiveData()

    fun getUser(){
        getUserAPI.getUser().enqueue(object : Callback<KerangkaResponse>{
            override fun onResponse(call: Call<KerangkaResponse>, response: Response<KerangkaResponse>) {
                val responData = response.body()
                val res = responData?.data
                if (res == null) {
                    println("NAMA FILE NULL")
                } else {
                    val gson = Gson()
                    val listKegiatan: Type = object : TypeToken<List<UserModel>?>() {}.type
                    val UserOutputList: List<UserModel> =
                            gson.fromJson(gson.toJson(res), listKegiatan)
                    dataUser.value = UserOutputList
                    val responBody : ResponseBody? = null
                    responBody?.close()
                }
            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}