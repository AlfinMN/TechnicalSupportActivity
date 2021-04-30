package com.projectassyifa.technicalsupportactivities.data.login.api

import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginAPI {
    @POST("api/krt/login")
    fun loginUser(@Body userLoginModel: UserLoginModel): Call<KerangkaResponse>
}