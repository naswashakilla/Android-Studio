package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.annotation.DrawableRes

@Parcelize
data class MenuItem(
    val nama: String,
    val harga: Int,
    var jumlah: Int = 0,
    @DrawableRes val imageResId: Int
) : Parcelable