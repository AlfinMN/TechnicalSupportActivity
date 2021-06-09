package com.projectassyifa.technicalsupportactivities.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.user.api.GetUserByIdAPI
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class UserByIdRepo @Inject constructor(val getUserByIdAPI: GetUserByIdAPI) {
    var dataUserById : MutableLiveData<List<UserModel>> = MutableLiveData()

    fun userById(id_akun : String){
        getUserByIdAPI.userById(id_akun).enqueue(object :  Callback<KerangkaResponse> {
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
                    dataUserById.value = UserOutputList
                    val responBody : ResponseBody? = null


                        responBody?.close()



                    println("reponse BY ID $res")
                }
            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}