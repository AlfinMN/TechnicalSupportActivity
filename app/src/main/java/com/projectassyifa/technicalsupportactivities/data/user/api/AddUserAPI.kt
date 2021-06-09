package com.projectassyifa.technicalsupportactivities.data.user.api


import com.projectassyifa.technicalsupportactivities.data.user.model.UserAddModel
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AddUserAPI {
    @POST("api/krt/add_user")
    fun addUser(@Body userAddModel: UserAddModel): Call<KerangkaResponse>
}