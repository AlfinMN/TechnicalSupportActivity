package com.projectassyifa.technicalsupportactivities.data.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.data.user.repository.UserByIdRepo
import javax.inject.Inject

class UserByIdViewModel @Inject constructor(var userByIdRepo: UserByIdRepo){
    val userById : MutableLiveData<List<UserModel>>? = userByIdRepo.dataUserById
    fun userById(id_akun : String ){
        userByIdRepo.userById(id_akun)
    }
}