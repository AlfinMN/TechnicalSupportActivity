package com.projectassyifa.technicalsupportactivities.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.projectassyifa.technicalsupportactivities.R
import kotlinx.android.synthetic.main.activity_insert_kegiatan.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import android.Manifest
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.icu.number.NumberFormatter.with
import android.provider.MediaStore
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.graphics.get
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.technicalsupportactivities.container.MyApplication
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel.AddKegiatanViewModel
import com.projectassyifa.technicalsupportactivities.utils.AlertSaved
import com.projectassyifa.technicalsupportactivities.utils.UploadingCamera
import com.squareup.picasso.Picasso
import javax.inject.Inject

class InsertKegiatan : AppCompatActivity() {

    private val cameraRequestId  = 1222
    var sharedPreferences : SharedPreferences? = null
    @Inject
    lateinit var addKegiatanViewModel: AddKegiatanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_kegiatan)
        (applicationContext as MyApplication).applicationComponent.inject(this)

        sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )

        if (ContextCompat.checkSelfPermission(
                        applicationContext,Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    cameraRequestId
            )
        chose_file.setOnClickListener {
//            EasyImage.openCamera(this,1)
//EasyImage.openGallery(this,1)
        val popMenu = PopupMenu(this,it)
            popMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.opencamera -> {
                        EasyImage.openCamera(this,1)
                        true
                    }
                    R.id.openfile -> {
                        EasyImage.openGallery(this,1)
                        true
                    }
                    else -> false
                }
            }
            popMenu.inflate(R.menu.upload)
            popMenu.show()
        }


        val idAkun = sharedPreferences?.getString(
                getString(R.string.id_akun),
                getString(R.string.default_value)
        )
        val idPegawai = sharedPreferences?.getString(
                getString(R.string.id_pegawai),
                getString(R.string.default_value)
        )
        val namaPegawai = sharedPreferences?.getString(
                getString(R.string.nama_pegawai),
                getString(R.string.default_value)
        )
        println("ID AKUN $idAkun")

        //simpan kegiatan
        savekegiatan.setOnClickListener {
            val addKegiatanContent = AddKegiatanModel()
            addKegiatanContent.id_akun = idPegawai.toString()
            addKegiatanContent.nama_pegawai = namaPegawai.toString()
            addKegiatanContent.aktivitas = aktivitasinput.text.toString()
            addKegiatanContent.lokasi = lokasiInput.text.toString()
            addKegiatanContent.remark = remarkinput.text.toString()

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
                addKegiatanViewModel.addKegiatan(addKegiatanContent,fileImage!!)
                println("DATA ${addKegiatanContent.id_akun} , ${addKegiatanContent.aktivitas},${addKegiatanContent.lokasi}  ,${addKegiatanContent.remark} ,${fileImage!!.name}")
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }

        }
    }

    var fileImage: File? = null
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
        EasyImage.handleActivityResult(requestCode,resultCode,data,this,object :DefaultCallback(){
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
                nama_file.setText(imageFile?.name)
                val requestOptions = RequestOptions().error(R.drawable.upload)
                Glide.with(this@InsertKegiatan)
                    .load(imageFile)
                    .apply(requestOptions)
                    .into(image_upload)
            }

        });

    }
}