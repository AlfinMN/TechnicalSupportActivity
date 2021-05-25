package com.projectassyifa.technicalsupportactivities.data.Kegiatan.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UpdateStatusAPI {
    @Multipart
    @POST("api/krt/editStatus")
    fun updateStatus(@Part("id_akun")id_akun : RequestBody,
                     @Part("id")id : RequestBody,
                     @Part("status")status: RequestBody,
                     @Part foto_proses : MultipartBody.Part?= null,
                     @Part foto_akhir : MultipartBody.Part?= null
    ):Call<KerangkaResponse>
}