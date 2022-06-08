package com.example.githubbrowser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/repos/{username}/{reponame}")
    fun getRepos(@Path("username") username : String, @Path("reponame") repo : String) : Call<RepoResponse>

}