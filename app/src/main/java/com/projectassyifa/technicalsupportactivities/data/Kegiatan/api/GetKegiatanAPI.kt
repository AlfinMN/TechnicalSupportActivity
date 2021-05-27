package com.projectassyifa.technicalsupportactivities.data.Kegiatan.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetKegiatanAPI {
    @GET("api/krt")
    fun KegiatanUser():Call<KerangkaResponse>
}