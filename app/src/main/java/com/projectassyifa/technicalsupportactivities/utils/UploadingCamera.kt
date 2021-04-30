package com.projectassyifa.technicalsupportactivities.utils

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectassyifa.technicalsupportactivities.R

class UploadingCamera  (val mActivity: Activity) {
    private lateinit var isdialog: AlertDialog

    fun startLoading(){

        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.activity_uploading_camera,null)

        val bulider = AlertDialog.Builder(mActivity)

        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}