package com.projectassyifa.technicalsupportactivities.data.user.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetUserByIdAPI {
    @GET("api/krt/akun/{id_akun}")
    fun userById(@Path("id_akun")id_akun : String): Call<KerangkaResponse>
}