package com.projectassyifa.technicalsupportactivities.container

import com.projectassyifa.technicalsupportactivities.activity.InsertKegiatan
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.screen.home.HomeScreen
import com.projectassyifa.technicalsupportactivities.screen.login.LoginScreen
import dagger.Component


@Component(modules = [NetworkModul::class])
interface ApplicationComponent {
    fun inject(userLogin : LoginScreen)
    fun inject(insertKegiatan: InsertKegiatan)
    fun inject(homeScreen: HomeScreen)
}