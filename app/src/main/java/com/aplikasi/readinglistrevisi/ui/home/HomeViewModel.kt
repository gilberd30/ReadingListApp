package com.aplikasi.readinglistrevisi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "HOME FRAGMENT"
    }
    val text: LiveData<String> = _text
}