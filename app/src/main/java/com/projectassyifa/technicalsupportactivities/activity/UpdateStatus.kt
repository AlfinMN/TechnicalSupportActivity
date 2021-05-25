package com.projectassyifa.technicalsupportactivities.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.technicalsupportactivities.R
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.UpdateStatusViewModel
import com.projectassyifa.technicalsupportactivities.utils.AlertSaved
import com.projectassyifa.technicalsupportactivities.utils.UploadingCamera
import kotlinx.android.synthetic.main.activity_insert_kegiatan.*
import kotlinx.android.synthetic.main.activity_insert_kegiatan.chose_file
import kotlinx.android.synthetic.main.activity_update_status.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import javax.inject.Inject

class UpdateStatus : AppCompatActivity() {
    private val cameraRequestId  = 1222
    var pilihStatus : String?  = null
    @Inject
    lateinit var updateStatusViewModel: UpdateStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_status)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        if (ContextCompat.checkSelfPermission(
                applicationContext, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                cameraRequestId
            )
        chose_file.setOnClickListener {
            EasyImage.openCamera(this,1)
        }
        var bundle = intent.extras
        println("MASUK UPDATE STATUS")
        println(bundle?.getString("id"))
        println(bundle?.getString("id_akun"))
        pilih_status.setOnCheckedChangeListener { group, checkedId ->
            var rb : RadioButton = findViewById<RadioButton>(checkedId)
            if (rb != null){
               pilihStatus = rb.text.toString()
            }
        }

        btn_simpan.setOnClickListener {
            val updateStatusContent = AddKegiatanModel()
            updateStatusContent.id_akun = bundle?.getString("id_akun").toString()
            updateStatusContent.id = bundle?.getString("id").toString()
            updateStatusContent.status = pilihStatus.toString()
            println(updateStatusContent.status)
//            updateStatusContent.status = input_status.text.toString()
            if (fileImage == null){
                Toast.makeText(
                    this,
                    "Tidak ada file yang di pilih!!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val loading = AlertSaved(this)
                loading.startLoading()
                val handler = Handler()
                handler.postDelayed(object :Runnable{
                    override fun run() {
                        loading.isDismiss()
                    }

                },3000)

                updateStatusViewModel.updateStatus(updateStatusContent,fileImage!!)
                println("DATA ${updateStatusContent.id},${updateStatusContent.id_akun},${fileImage!!.name}")
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }

        }
    }


    var fileImage : File?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val loading = UploadingCamera(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)
        EasyImage.handleActivityResult(requestCode,resultCode,data,this,object : DefaultCallback(){
            var selectedImage = data?.data
            override fun onImagePicked(
                imageFile: File?,
                source: EasyImage.ImageSource?,
                type: Int
            ) {
                fileImage = imageFile
                var namaImage =imageFile?.name
                println("HASIL IMAGE $fileImage")
                println("NAMA IMAGE $namaImage")
                nama_file1.setText(imageFile?.name)
                val requestOptions = RequestOptions().error(R.drawable.upload)
                Glide.with(this@UpdateStatus)
                    .load(imageFile)
                    .apply(requestOptions)
                    .into(image_upload1)
            }

        });
    }
}