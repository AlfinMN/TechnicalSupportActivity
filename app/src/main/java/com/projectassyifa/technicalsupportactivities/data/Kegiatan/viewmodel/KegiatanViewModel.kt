package com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.KegiatanRepo
import javax.inject.Inject

class KegiatanViewModel @Inject constructor(var dataKegiatanRepo: KegiatanRepo){
    var dataKegiatan : MutableLiveData<List<AddKegiatanModel>>? = dataKegiatanRepo.dataKegiatan

    fun kegiatanUser(){
        dataKegiatanRepo.kegiatanUser()
    }
}