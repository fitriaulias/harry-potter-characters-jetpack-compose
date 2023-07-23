package com.fitri.harrypottercharacters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: String,
    val name: String,
    val photoUrl: String,
    val description: String,
) : Parcelable