package com.fitri.harrypottercharacters.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitri.harrypottercharacters.data.CharactersRepository
import com.fitri.harrypottercharacters.ui.common.UiState
import com.fitri.harrypottercharacters.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CharactersRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Character>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Character>> get() = _uiState

    fun getDetailById(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCharacterById(id))
        }
    }
}