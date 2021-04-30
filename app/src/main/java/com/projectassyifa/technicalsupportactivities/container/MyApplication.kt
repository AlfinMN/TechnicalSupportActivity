package com.projectassyifa.technicalsupportactivities.container

import android.app.Application

class MyApplication :Application() {
    val applicationComponent:ApplicationComponent = DaggerApplicationComponent.create()
}