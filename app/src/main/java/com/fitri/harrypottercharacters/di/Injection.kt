package com.fitri.harrypottercharacters.di

import com.fitri.harrypottercharacters.data.CharactersRepository

object Injection {
    fun provideRepository(): CharactersRepository {
        return CharactersRepository.getInstance()
    }
}