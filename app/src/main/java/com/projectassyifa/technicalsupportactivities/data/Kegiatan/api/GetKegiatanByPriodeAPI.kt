package com.projectassyifa.technicalsupportactivities.data.Kegiatan.api

import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetKegiatanByPriodeAPI {
    @GET("api/krt/priode/{id_akun}")
    fun kegiatanByPriode(@Path("id_akun")id_akun : String,
                        @Query("tgl_awal")tgl_awal : String,
                         @Query("tgl_akhir")tgl_akhir: String
    ):Call<KerangkaResponse>
}