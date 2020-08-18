package com.example.ssc.ui.PlaceFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento de Sucursales"
    }
    val text: LiveData<String> = _text
}