package com.example.dinosaursapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinosaursPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)