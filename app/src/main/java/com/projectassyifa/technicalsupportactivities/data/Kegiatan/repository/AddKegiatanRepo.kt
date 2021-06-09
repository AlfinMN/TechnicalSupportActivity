package com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository

import android.system.Os.close
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.AddKegiatanAPI
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class AddKegiatanRepo @Inject constructor(val addKegiatanAPI: AddKegiatanAPI) {
    var kegiatanResponse = MutableLiveData<KerangkaResponse>()

    fun addKegiatan(data:AddKegiatanModel,file :File){
        val id_akun = convert(data.id_akun)
        val nama_pegawai = convert(data.nama_pegawai)
        val lokasi = convert(data.lokasi)
        val aktivitas = convert(data.aktivitas)
        val remark = convert(data.remark)
        val foto_awal = convertFile(file)

        addKegiatanAPI.addKegiatan(id_akun,nama_pegawai,aktivitas,lokasi,remark,foto_awal).enqueue(object : Callback<KerangkaResponse> {
            override fun onResponse(call: Call<KerangkaResponse>, response: Response<KerangkaResponse>) {
               val res = response.body()
                println("MASUK REPO ADD ${res}")
                res?.status
                val stringResponse = Gson().toJson(res)
                val dataKegiatanContent = Gson().fromJson<KerangkaResponse>(stringResponse,KerangkaResponse::class.java)
                kegiatanResponse.value =dataKegiatanContent

            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                t.printStackTrace()
                println("ADD KEGIATAN GAGAL")
            }

        })

    }

    private fun convert (string :String ) : RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(),string)
    }

    private fun convertFile(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("foto_awal",file.name,reqFile)
    }
}