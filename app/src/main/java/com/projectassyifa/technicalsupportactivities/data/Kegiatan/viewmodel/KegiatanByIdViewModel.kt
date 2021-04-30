package com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.AddKegiatanRepo
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.KegiatanByIdRepo
import javax.inject.Inject

class KegiatanByIdViewModel @Inject constructor(var kegiatanRepo: KegiatanByIdRepo) {

    val dataById : MutableLiveData<List<AddKegiatanModel>>? = kegiatanRepo.dataById
    fun kegiatanById(id_akun : String){
        kegiatanRepo.kegiatanById(id_akun)
        println("masuk viewmodel")
        println("ID $id_akun")
    }
}