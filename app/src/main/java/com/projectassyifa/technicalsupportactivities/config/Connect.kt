package com.projectassyifa.technicalsupportactivities.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connect {
    companion object {
        val BASE_URL_GLOBAL = "http://202.62.9.138:1234/rest-api/"
        val BASE_URL_ALT =  "http://202.62.9.138:1234/"

        fun urlGlobal(): Retrofit {
            val koneksi = Retrofit
                .Builder()
                .baseUrl(BASE_URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return koneksi
        }

        fun urlAlternatif(): Retrofit {
            val connection = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_ALT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return connection
        }
    }
}