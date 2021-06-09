package com.projectassyifa.technicalsupportactivities.screen.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModel
import com.projectassyifa.technicalsupportactivities.data.login.model.UserLoginModelAlt
import com.projectassyifa.technicalsupportactivities.data.login.viewmodel.UserLoginViewModel
import com.projectassyifa.technicalsupportactivities.data.login.viewmodel.UserLoginViewModelAlt
import kotlinx.android.synthetic.main.fragment_login_screen.*
import javax.inject.Inject

//
//class LoginScreen : Fragment(), View.OnClickListener {
//
//    var sharedPreferences: SharedPreferences? = null
//    lateinit var navController: NavController
//
//    @Inject
//    lateinit var userLoginViewModel: UserLoginViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
//        sharedPreferences = activity?.getSharedPreferences(
//                getString(R.string.shared_preference_name),
//                Context.MODE_PRIVATE
//        )
//    }
//
//            override fun onCreateView(
//                inflater: LayoutInflater, container: ViewGroup?,
//                savedInstanceState: Bundle?
//            ): View? {
//                // Inflate the layout for this fragment
//                return inflater.inflate(R.layout.fragment_login_screen, container, false)
//            }
//
//            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//                super.onViewCreated(view, savedInstanceState)
//
//                if(sharedPreferences?.contains(getString(R.string.username))!! && sharedPreferences?.contains(getString(R.string.login_method_key))!!)
//                {
//                    view.findNavController().navigate(R.id.action_loginScreen_to_homeActivity)
//                }
//                navController = Navigation.findNavController(view)
//                userLoginButton.setOnClickListener(this)
//                userLoginViewModel.userLoginResponse.observe(
//                        viewLifecycleOwner,androidx.lifecycle.Observer {
//                            if (it.status != true){
//                                Toast.makeText(
//                                        this.context,
//                                        "Username or password salah",
//                                        Toast.LENGTH_SHORT
//                                ).show()
//                            } else {
//                                Toast.makeText(this.context, "Login Success", Toast.LENGTH_SHORT).show()
//                                userLoginViewModel.userLoginResponseData.observe(viewLifecycleOwner, Observer {
//                                    if (it != null) {
//                                        with(sharedPreferences?.edit()) {
//                                            this?.putString(
//                                            getString(R.string.username),
//                                            it.username
//                                            )
//                                            this?.putString(
//                                                    getString(R.string.firstname),
//                                                    it.firstname
//                                            )
//                                            this?.putString(
//                                                    getString(R.string.id_akun),
//                                                    it.id_akun
//                                            )
//                                            this?.putString(
//                                                getString(R.string.lastname),
//                                                it.lastname
//                                            )
//                                            this?.putString(
//                                                getString(R.string.akun_level),
//                                                it.akun_level
//                                            )
//                                            this?.putString(
//                                                    getString(R.string.login_method_key),
//                                                    "appLogin"
//                                            )
//                                            this?.commit()
//                                        }
//                                        navController.navigate(R.id.action_loginScreen_to_homeActivity)
//                                    }
//                                })
//                            }
//                }
//                )
//
//    }
//
//    override fun onClick(v: View?) {
//        when(v) {
//            userLoginButton -> {
//                val userLoginModel = UserLoginModel(
//                        username = usernameInput.text.toString(),
//                        password=  userPasswordInput.text.toString()
//                )
//                if (usernameInput.text.toString() == ""  && userPasswordInput.text.toString() == ""
//                ){
//                    Toast.makeText(this.context, "Isi semua form", Toast.LENGTH_SHORT).show()
//                } else {
//                    userLoginViewModel.loginUser(userLoginModel,requireContext())
//                }
//            }
//        }
//    }
//
//}


class LoginScreen : Fragment(), View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null
    lateinit var navController: NavController

    @Inject
    lateinit var userLoginViewModelAlt: UserLoginViewModelAlt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        sharedPreferences = activity?.getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(sharedPreferences?.contains(getString(R.string.username))!! && sharedPreferences?.contains(getString(R.string.login_method_key))!!)
        {
            view.findNavController().navigate(R.id.action_loginScreen_to_homeActivity)
        }
        navController = Navigation.findNavController(view)
        userLoginButton.setOnClickListener(this)
        userLoginViewModelAlt.userLogin.observe(
                viewLifecycleOwner,androidx.lifecycle.Observer {
            if (it.status != true){
                Toast.makeText(
                        this.context,
                        "Username or password salah",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this.context, "Login Success", Toast.LENGTH_SHORT).show()
                userLoginViewModelAlt.userLoginResponse.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        with(sharedPreferences?.edit()) {
                            this?.putString(getString(R.string.username), it.email)
                            this?.putString(getString(R.string.id_pegawai), it.id_pegawai)
                            this?.putString(getString(R.string.nama_pegawai), it.nama_pegawai)
                            this?.putString(getString(R.string.nip), it.nip)
                            this?.putString(getString(R.string.foto), it.foto)
                            this?.putString(getString(R.string.no_tlp), it.no_telp)
                            this?.putString(getString(R.string.unit), it.unit)
                            this?.putString(
                                    getString(R.string.login_method_key),
                                    "appLogin"
                            )
                            this?.commit()
                        }
                        navController.navigate(R.id.action_loginScreen_to_homeActivity)
                    }
                })
            }
        }
        )

    }

    override fun onClick(v: View?) {
        when(v) {
            userLoginButton -> {
                val userLoginModel = UserLoginModelAlt(
                        username = usernameInput.text.toString(),
                        password=  userPasswordInput.text.toString()
                )
                if (usernameInput.text.toString() == ""  && userPasswordInput.text.toString() == ""
                ){
                    Toast.makeText(this.context, "Isi semua form", Toast.LENGTH_SHORT).show()
                } else {
                    userLoginViewModelAlt.loginUserAlt(userLoginModel,requireContext())
                }
            }
        }
    }

}