package com.projectassyifa.technicalsupportactivities.data.login.model

class UserLoginModel (
    var username: String = "default",
    var password: String = "default"
){}

class UserLoginResponseModel (
        var id_akun : String = " ",
        var username : String = " ",
        var firstname : String = " ",
        var lastname : String = " ",
        var akun_level : String = " "
        ){}