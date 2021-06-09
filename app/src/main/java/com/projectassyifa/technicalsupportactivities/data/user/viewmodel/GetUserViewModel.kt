package com.projectassyifa.technicalsupportactivities.data.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.data.user.repository.GetUserRepo
import javax.inject.Inject

class GetUserViewModel @Inject constructor(var getUserRepo: GetUserRepo) {
    val dataUser : MutableLiveData<List<UserModel>>?= getUserRepo.dataUser

    fun getUser(){
        getUserRepo.getUser()
    }
}