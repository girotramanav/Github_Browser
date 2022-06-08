package com.example.githubbrowser

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRepoViewModel(application : Application) : AndroidViewModel(application) {

    val tempArrayList = ArrayList<RepoResponse>()

    var result  = MutableLiveData<ArrayList<RepoResponse>>()
    init {
        result.value = arrayListOf()
    }

    fun getUserRepo(userName : String , repoName : String){

        val service = RetrofitInstance.retrofitInstance?.create(Api::class.java)

        viewModelScope.launch(Dispatchers.IO){
            val call = service?.getRepos(userName , repoName)
            call?.enqueue(object : Callback<RepoResponse>{
                override fun onResponse(
                    call: Call<RepoResponse>,
                    response: Response<RepoResponse>
                ) {
                    if(response.code()==404){
                        val temp = result.value
                        result.value = tempArrayList
                        result.value = temp!!
                        Toast.makeText(
                            getApplication(),
                            "No Repository Found aginst the input data!!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else
                    response.body()?.let {
                        val newResult = result.value
                        newResult?.add(response.body()!!)
                        result.value = newResult!!
                        Log.d("MyTAG" , "Added to list size:" + result.value!!.size)
                    }

                }

                override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                    Log.d("MyTAG" , "Call Failed " + t.message)
                }

            })
        }

    }

    @JvmName("getResult1")
    fun getResult(): ArrayList<RepoResponse> {
        return result.value!!
    }

}