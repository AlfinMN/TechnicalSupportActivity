package com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel

import androidx.lifecycle.ViewModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.AddKegiatanRepo
import java.io.File
import javax.inject.Inject

class AddKegiatanViewModel @Inject constructor(var addKegiatanRepo: AddKegiatanRepo) :ViewModel() {
    fun addKegiatan(addKegiatanModel: AddKegiatanModel,file :File){
        addKegiatanRepo.addKegiatan(addKegiatanModel,file)
    }
}