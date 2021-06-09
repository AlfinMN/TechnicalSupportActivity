package com.projectassyifa.technicalsupportactivities.container

import com.projectassyifa.technicalsupportactivities.activity.*
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanAllUserAdapter
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.adapter.KegiatanByIdAdapter
import com.projectassyifa.technicalsupportactivities.data.user.adapter.UserAdapter
import com.projectassyifa.technicalsupportactivities.screen.home.HomeScreen
import com.projectassyifa.technicalsupportactivities.screen.login.LoginScreen
import dagger.Component


@Component(modules = [NetworkModul::class])
interface ApplicationComponent {
    fun inject(userLogin : LoginScreen)
    fun inject(insertKegiatan: InsertKegiatan)
    fun inject(homeScreen: HomeScreen)
    fun inject(updateStatus: UpdateStatus)
    fun inject(reportActivity: ReportActivity)
    fun inject(reportAllUser: ReportAllUser)
    fun inject(kegiatanAllUserAdapter: KegiatanAllUserAdapter)
    fun inject(userAdapter: UserAdapter)
    fun inject(addJob: AddJob)
    fun inject(listUserActivity: ListUserActivity)
    fun inject(addAnggotaActivity: AddAnggotaActivity)
}