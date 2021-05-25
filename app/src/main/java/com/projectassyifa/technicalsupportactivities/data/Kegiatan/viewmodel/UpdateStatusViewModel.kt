package com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel

import androidx.lifecycle.ViewModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.UpdateStatusRepo
import java.io.File
import javax.inject.Inject

class UpdateStatusViewModel @Inject constructor(var updateStatusRepo: UpdateStatusRepo) :ViewModel() {

    fun updateStatus(addKegiatanModel: AddKegiatanModel ,file : File){
        updateStatusRepo.updateStatus(addKegiatanModel,file)
    }
}