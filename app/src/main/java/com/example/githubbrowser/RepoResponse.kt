package com.example.githubbrowser

data class RepoResponse(
    val id : Int,
    val name : String = "Null",
    val html_url : String = "",
    val description : String = ""
)
