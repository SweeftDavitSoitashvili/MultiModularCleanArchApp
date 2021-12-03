package com.example.dashboard.data.models

import com.google.gson.annotations.SerializedName


data class Response(
    val page : Int,
    @SerializedName("data")
    val resources : List<ResourceDto>
)

data class ResourceDto(
    val id : Int,
    val name : String,
    val year : Int,
    val color : String,
    @SerializedName("pantone_value")
    val pantoneValue : String
)