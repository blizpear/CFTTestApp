package com.blizpear.cfttestapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonData(
    val name: String,
    val surname: String,
    val passwd: String,
    val date: String
) : Parcelable
