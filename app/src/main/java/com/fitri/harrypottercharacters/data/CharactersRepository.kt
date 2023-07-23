package com.fitri.harrypottercharacters.data

import com.fitri.harrypottercharacters.model.Character
import com.fitri.harrypottercharacters.model.CharactersData

class CharactersRepository {
    private val characterList = mutableListOf<Character>()

    init {
        if(characterList.isEmpty()) {
            CharactersData.characters.forEach {
                characterList.add(it)
            }
        }
    }

    fun getAllCharacters(): List<Character> {
        return characterList
    }

    fun getCharacterById(id: String): Character {
        return characterList.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: CharactersRepository? = null

        fun getInstance(): CharactersRepository =
            instance ?: synchronized(this) {
                CharactersRepository().apply {
                    instance = this
                }
            }
    }
}