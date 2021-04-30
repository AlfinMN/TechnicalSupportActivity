package com.projectassyifa.technicalsupportactivities.data.Kegiatan.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AddKegiatanAPI {
    @Multipart
    @POST ("api/krt/dataActivities")
    fun addKegiatan(@Part("id_akun")id_akun : RequestBody,
                    @Part("aktivitas") aktivitas :RequestBody,
                    @Part("lokasi") lokasi :RequestBody,
                    @Part("remark") remark :RequestBody,
                    @Part foto_awal : MultipartBody.Part? = null
    ): Call<KerangkaResponse>
}