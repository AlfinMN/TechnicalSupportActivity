package com.projectassyifa.technicalsupportactivities.data.login.viewmodel

import android.content.Context
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModelAlt
import com.projectassyifa.technicalsupportactivities.data.login.repository.UserLoginRepositoryAlt
import javax.inject.Inject

class UserLoginViewModelAlt @Inject constructor(var userLoginRepo: UserLoginRepositoryAlt){
    var userLogin = userLoginRepo.userLogin
    var userLoginResponse = userLoginRepo.userLoginResponse

    fun loginUserAlt(userLoginModel: UserLoginModelAlt, context: Context){
        userLoginRepo.loginUserAlt(userLoginModel,context)
        println("DATA VM ${userLoginModel.username},${userLoginModel.password}")
    }
}