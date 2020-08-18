package com.example.ssc.ui.HomeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento de Inicio"
    }
    val text: LiveData<String> = _text
}