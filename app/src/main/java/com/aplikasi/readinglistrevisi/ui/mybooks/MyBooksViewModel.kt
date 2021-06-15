package com.aplikasi.readinglistrevisi.ui.mybooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyBooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "MY BOOKS FRAGMENT"
    }
    val text: LiveData<String> = _text
}