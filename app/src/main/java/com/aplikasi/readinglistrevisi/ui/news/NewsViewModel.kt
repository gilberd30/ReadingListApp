package com.aplikasi.readinglistrevisi.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "NEWS FRAGMENT"
    }
    val text: LiveData<String> = _text
}