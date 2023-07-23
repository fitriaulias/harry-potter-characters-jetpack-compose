package com.fitri.harrypottercharacters.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitri.harrypottercharacters.data.CharactersRepository
import com.fitri.harrypottercharacters.ui.common.UiState
import com.fitri.harrypottercharacters.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharactersRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Character>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Character>>> get() = _uiState

    fun getAllCharacters() {
        viewModelScope.launch {
            repository.getAllCharacters()
                try {
                    val characters = repository.getAllCharacters()
                    _uiState.value = UiState.Success(characters)
                } catch (e: Exception) {
                    _uiState.value = UiState.Error(e.message.toString())
                }
        }
    }
}