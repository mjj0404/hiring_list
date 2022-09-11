package com.fetchreward.hiring.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HiringItem (
    val id: Int,
    val listId: Int,
    val name: String
): Parcelable