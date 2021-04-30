package com.projectassyifa.technicalsupportactivities.data.Kegiatan.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetKegiatanByIdAPI {
    @GET("api/krt/kegiatanbyid/{id_akun}")
    fun kegiatanById(@Path("id_akun")id_akun : String) :Call<KerangkaResponse>
}