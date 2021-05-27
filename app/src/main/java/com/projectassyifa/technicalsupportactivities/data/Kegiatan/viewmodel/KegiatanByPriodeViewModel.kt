package com.projectassyifa.technicalsupportactivities.data.Kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.model.AddKegiatanModel
import com.projectassyifa.technicalsupportactivities.data.Kegiatan.repository.KegiatanByPriodeRepo
import javax.inject.Inject

class KegiatanByPriodeViewModel @Inject constructor(var kegiatanByPriodeRepo:KegiatanByPriodeRepo) {
    val dataByPriode : MutableLiveData<List<AddKegiatanModel>>?= kegiatanByPriodeRepo.dataByPriode
    fun kegiatanByPriode(id_akun : String , tgl_awal : String , tgl_akhir : String){
        kegiatanByPriodeRepo.kegiatanByPriode(id_akun, tgl_awal, tgl_akhir)
        println("masuk viewmodel")
        println("ID $id_akun,TGL AWAL $tgl_awal, TGL AKHIR $tgl_akhir")
    }
}