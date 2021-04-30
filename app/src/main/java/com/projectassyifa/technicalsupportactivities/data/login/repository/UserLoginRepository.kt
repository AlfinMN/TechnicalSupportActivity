package com.projectassyifa.technicalsupportactivities.data.login.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.technicalsupportactivities.data.login.api.UserLoginAPI
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModel
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginResponseModel
import com.projectassyifa.technicalsupportactivities.utils.KerangkaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class UserLoginRepository @Inject constructor( val userLoginAPI: UserLoginAPI) {
    var userLogin = MutableLiveData<KerangkaResponse>()
    var userLoginResponse = MutableLiveData<UserLoginResponseModel>()

    fun loginUser(userLoginModel: UserLoginModel,context: Context){
        userLoginAPI.loginUser(userLoginModel).enqueue(object :Callback<KerangkaResponse>{
            override fun onResponse(
                call: Call<KerangkaResponse>,
                response: Response<KerangkaResponse>
            ) {
                println(response.body())
                println(response.code())
                if(response.code()==200) {
                    val res = response.body()
                    val resData = res?.data
                    val gson = Gson()
                    val listOfMyClassObject: Type = object : TypeToken<List<UserLoginResponseModel>>() {}.type
                    val outputList: List<UserLoginResponseModel> = gson.fromJson(gson.toJson(resData), listOfMyClassObject)


                    println("ini outputlist")
                    println(outputList)


                    userLogin.value = res
                    userLoginResponse.value = outputList[0]

                    println("masuk respone nih")
                    println(userLogin.value)
                    Toast.makeText(context,"Login Success ", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(context,"Login Failed", Toast.LENGTH_SHORT).show()
                    println("MASUK SINI")
                }

            }

            override fun onFailure(call: Call<KerangkaResponse>, t: Throwable) {
                t.printStackTrace()
                print("MASUK ONFAILURE")
            }

        })
    }
}