package com.example.mcore.models.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceData(
    val name: String
) : Parcelable
