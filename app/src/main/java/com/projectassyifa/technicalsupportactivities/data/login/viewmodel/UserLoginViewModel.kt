package com.projectassyifa.technicalsupportactivities.data.login.viewmodel

import android.content.Context
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModel
import com.projectassyifa.technicalsupportactivities.data.login.repository.UserLoginRepository
import javax.inject.Inject

class UserLoginViewModel @Inject constructor(var userLoginRepository: UserLoginRepository) {
    var userLoginResponse = userLoginRepository.userLogin
    var userLoginResponseData = userLoginRepository.userLoginResponse

    fun loginUser(userLoginModel: UserLoginModel,context: Context){
        userLoginRepository.loginUser(userLoginModel,context)
    }

}