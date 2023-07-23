package com.fitri.harrypottercharacters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitri.harrypottercharacters.data.CharactersRepository
import com.fitri.harrypottercharacters.ui.screen.detail.DetailViewModel
import com.fitri.harrypottercharacters.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: CharactersRepository) :
ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}