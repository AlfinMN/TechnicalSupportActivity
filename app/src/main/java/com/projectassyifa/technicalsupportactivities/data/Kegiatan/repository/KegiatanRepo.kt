package com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.GetKegiatanAPI
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class KegiatanRepo @Inject constructor(val getKegiatanAPI: GetKegiatanAPI){
    var dataKegiatan : MutableLiveData<List<AddKegiatanModel>> = MutableLiveData()

    fun kegiatanUser(){
        getKegiatanAPI.KegiatanUser().enqueue(object : Callback<KerangkaResponse> {
            override fun onResponse(call: Call<KerangkaResponse>, response: Response<KerangkaResponse>) {
                val responData = response.body()
                val res = responData?.data
                if (res == null) {
                    println("NAMA FILE NULL")
                } else {
                    val gson = Gson()
                    val listKegiatan: Type = object : TypeToken<List<AddKegiatanModel>?>() {}.type
                    val kegiatanOutputList: List<AddKegiatanModel> =
                            gson.fromJson(gson.toJson(res), listKegiatan)
                    dataKegiatan.value = kegiatanOutputList
                    val responBody : ResponseBody? = null


                    responBody?.close()

                    println("reponse $res")
                    println("output $kegiatanOutputList")
                }
            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }


        })
    }
}