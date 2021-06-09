package com.projectassyifa.technicalsupportactivities.data.user.model

class UserModel {
    var id_akun : String = ""
    var username : String = ""
    var firstname : String = ""
    var lastname : String = ""
    var akun_level : String =""
    var password : String =""
}
class UserAddModel (
        var username: String = "default",
        var firstname: String = "default",
        var lastname: String = "default",
        var password: String = "default"
){}