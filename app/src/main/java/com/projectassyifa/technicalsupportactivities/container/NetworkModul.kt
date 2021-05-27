package com.projectassyifa.technicalsupportactivities.container

import com.projectassyifa.technicalsupportactivities.config.Connect
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.api.*
import com.projectassyifa.technicalsupportactivities.data.login.api.UserLoginAPI
import com.projectassyifa.technicalsupportactivities.data.user.api.GetUserByIdAPI
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
    fun provideGetKegiatanAPI(): GetKegiatanAPI {
        return Connect.urlGlobal().create(GetKegiatanAPI::class.java)
    }
    @Provides
    fun provideGetKegiatanByIdAPI(): GetKegiatanByIdAPI {
        return Connect.urlGlobal().create(GetKegiatanByIdAPI::class.java)
    }
    @Provides
    fun provideGetUserByIdAPI(): GetUserByIdAPI {
        return Connect.urlGlobal().create(GetUserByIdAPI::class.java)
    }
    @Provides
    fun provideUpdateStatusAPI(): UpdateStatusAPI {
        return Connect.urlGlobal().create(UpdateStatusAPI::class.java)
    }
    @Provides
    fun provideGetKegiatanByPriodeAPI(): GetKegiatanByPriodeAPI {
        return Connect.urlGlobal().create(GetKegiatanByPriodeAPI::class.java)
    }
}