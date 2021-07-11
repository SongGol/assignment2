package com.example.assignment2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel: ViewModel() {
    val currentTitle: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val currentContent: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    public fun setValues(title: String?, content: String?) {
        currentTitle.value = title
        currentContent.value = content
    }
}