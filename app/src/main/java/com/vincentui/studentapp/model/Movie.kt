package com.vincentui.studentapp.model

data class Movie(
    val id:Int?,
    val title:String?,
    val actors:List<String>?,
    val details:MovieDetails?
    )

data class MovieDetails(
    val genre:String?,
    val director:String?,
    val year:Int?,
    val rating:Double?)
