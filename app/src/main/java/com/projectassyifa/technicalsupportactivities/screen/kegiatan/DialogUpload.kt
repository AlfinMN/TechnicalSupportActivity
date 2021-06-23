package com.projectassyifa.technicalsupportactivities.screen.kegiatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.projectassyifa.technicalsupportactivities.R

class DialogUpload : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dialog_upload, container, false)
        var rootView : View = inflater.inflate(R.layout.fragment_dialog_upload,container,false)
        return rootView
    }

}