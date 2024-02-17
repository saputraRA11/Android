package com.example.mydatastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// buat viewModel supaya nerima parameter contructor
class ViewModelFactory (private val pref:SettingPreferences) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_LIST")
    override fun <T:ViewModel> create(modelClass:Class<T>):T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: "+modelClass.name)
    }
}