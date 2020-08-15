package com.example.ssc.ui.PacksFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PacksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento de paquetes"
    }
    val text: LiveData<String> = _text
}