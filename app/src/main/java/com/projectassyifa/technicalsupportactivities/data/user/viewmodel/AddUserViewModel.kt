package com.projectassyifa.technicalsupportactivities.data.user.viewmodel

import com.projectassyifa.technicalsupportactivities.data.user.model.UserAddModel
import com.projectassyifa.technicalsupportactivities.data.user.model.UserModel
import com.projectassyifa.technicalsupportactivities.data.user.repository.AddUserRepo
import javax.inject.Inject

class AddUserViewModel @Inject constructor(var addUserRepo: AddUserRepo) {
    var addUserdata = addUserRepo.addUser
    fun addUser(userAddModel: UserAddModel){
        addUserRepo.AddUser(userAddModel)
    }
}