package com.projectassyifa.technicalsupportactivities.data.login.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.technicalsupportactivities.data.login.api.UserloginAltAPI
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModelAlt
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginResponseModelAlt
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class UserLoginRepositoryAlt @Inject constructor(val userloginAltApi : UserloginAltAPI) {
    var userLogin = MutableLiveData<KerangkaResponse>()
    var userLoginResponse = MutableLiveData<UserLoginResponseModelAlt>()


    fun loginUserAlt(userLoginModel: UserLoginModelAlt,context: Context){
        println("USER LOGIN ${userLoginModel.username},${userLoginModel.password}")
        userloginAltApi.loginUserAlt(userLoginModel).enqueue(object : Callback<KerangkaResponse> {
            override fun onResponse(call: Call<KerangkaResponse>, response: Response<KerangkaResponse>) {
                println("USER LOGIN repo ${userLoginModel.username},${userLoginModel.password}")
                println("RESPONSE BODY ${response.body()}")
                println("RESPONSE code ${response.code()}")
                if (response.code()==200){
                    val res = response.body()
                    val resData = res?.data
                    val gson = Gson()
                    val listOfMyClassObject: Type = object : TypeToken<List<UserLoginResponseModelAlt>>() {}.type
                    val outputList: List<UserLoginResponseModelAlt> = gson.fromJson(gson.toJson(resData), listOfMyClassObject)


                    println("ini outputlist")
                    println(outputList)


                    userLogin.value = res
                    userLoginResponse.value = outputList[0]

                    println("masuk respone nih")
                    println(userLogin.value)
                    Toast.makeText(context,"Login Success ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"Login Failed", Toast.LENGTH_SHORT).show()
                    println("USER NOT FOUND")
                }
            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                t.printStackTrace()
                print("MASUK ONFAILURE")
            }

        })
    }
}