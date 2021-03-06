package com.example.ssc.ui.PlaceFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ssc.R

class PlaceFragment : Fragment() {

    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        placeViewModel =
            ViewModelProviders.of(this).get(PlaceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_place, container, false)
        val textView: TextView = root.findViewById(R.id.text_place)
        placeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}