package com.example.favoriteteam.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.favoriteteam.R

class FenerbahceHistory : Fragment() {
    lateinit var backbtn : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.activity_fenerbahce_history, container, false)
        backbtn = v.findViewById(R.id.back_btn)

        backbtn.setOnClickListener {
            findNavController().popBackStack()

        }
        return v
    }
}