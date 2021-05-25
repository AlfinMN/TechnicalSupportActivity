package com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.UpdateStatusAPI
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject

class UpdateStatusRepo @Inject constructor(val updateStatusAPI: UpdateStatusAPI) {
    var kegiatanResponse = MutableLiveData<KerangkaResponse>()

    fun updateStatus(data : AddKegiatanModel,file :File){
        val id_akun = convert(data.id_akun)
        val id = convert(data.id)
        val status = convert(data.status)
//        val foto_proses = convertFileProses(file)
//        val foto_akhir = convertFile(file)

        if (data.status == "PROCESS") {
            val foto_proses = convertFileProses(file)

            updateStatusAPI.updateStatus(id_akun,id,status,foto_proses,null).enqueue(object :Callback<KerangkaResponse>{
                override fun onResponse(
                        call: Call<KerangkaResponse>,
                        response: Response<KerangkaResponse>
                ) {
                    val res = response.body()
                    println("MASUK REPO UPDATE STTUS ${response}")
                    res?.status
                    val stringResponse = Gson().toJson(res)
                    val updateStatusContent = Gson().fromJson<KerangkaResponse>(stringResponse,KerangkaResponse::class.java)
                    kegiatanResponse.value=updateStatusContent
                }

                override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("UPDATE STATUS GAGAL")
                }

            })
        } else if (data.status == "CLOSE") {
            val foto_akhir = convertFile(file)
            updateStatusAPI.updateStatus(id_akun,id,status,null,foto_akhir).enqueue(object :Callback<KerangkaResponse>{
                override fun onResponse(
                        call: Call<KerangkaResponse>,
                        response: Response<KerangkaResponse>
                ) {
                    val res = response.body()
                    println("MASUK REPO UPDATE STTUS ${response}")
                    res?.status
                    val stringResponse = Gson().toJson(res)
                    val updateStatusContent = Gson().fromJson<KerangkaResponse>(stringResponse,KerangkaResponse::class.java)
                    kegiatanResponse.value=updateStatusContent
                }

                override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("UPDATE STATUS GAGAL")
                }

            })
        }


    }

    private fun convert(string: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(),string)
    }
    private fun convertFile(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("foto_akhir",file.name,reqFile)
    }
    private fun convertFileProses(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("foto_proses",file.name,reqFile)
    }
}