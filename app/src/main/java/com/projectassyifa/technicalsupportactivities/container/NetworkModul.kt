package com.projectassyifa.technicalsupportactivities.container

import com.projectassyifa.technicalsupportactivities.config.Connect
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.AddKegiatanAPI
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.GetKegiatanByIdAPI
import com.projectassyifa.technicalsupportactivities.data.login.api.UserLoginAPI
import dagger.Module
import dagger.Provides

@Module
class NetworkModul {
    @Provides
    fun provideUserLoginAPI(): UserLoginAPI {
        return Connect.urlGlobal().create(UserLoginAPI::class.java)
    }
    @Provides
    fun provideAddKegiatanAPI(): AddKegiatanAPI {
        return Connect.urlGlobal().create(AddKegiatanAPI::class.java)
    }
    @Provides
    fun provideGetKegiatanByIdAPI(): GetKegiatanByIdAPI {
        return Connect.urlGlobal().create(GetKegiatanByIdAPI::class.java)
    }
}