package com.example.dashboarddata.models

import com.google.gson.annotations.SerializedName

data class Response(
    val page : Int,
    @SerializedName("data")
    val resources : List<Resource>
)

data class Resource(
    val id : Int,
    val name : String,
    val year : Int,
    val color : String,
    @SerializedName("pantone_value")
    val pantoneValue : String
)