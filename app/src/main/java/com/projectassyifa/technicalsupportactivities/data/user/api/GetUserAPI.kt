package com.projectassyifa.technicalsupportactivities.data.user.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetUserAPI {
    @GET("api/krt/akun")
    fun getUser():Call<KerangkaResponse>
}