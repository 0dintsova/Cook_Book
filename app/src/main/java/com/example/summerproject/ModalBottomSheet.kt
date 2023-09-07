package com.example.summerproject

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.summerproject.databinding.ActivityContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: ActivityContentBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = ActivityContentBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }


}
