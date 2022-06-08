package com.example.githubbrowser

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.githubbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var viewModel : AddRepoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AddRepoViewModel::class.java]
        val listObserver = Observer<ArrayList<RepoResponse>>{
            onBackPressed()
        }
        setContentView(_binding.root)
        _binding.icAdd.setOnClickListener{
            findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_repositoryListFragment_to_addRepoFragment)
        }
        _binding.icBack.setOnClickListener {
            onBackPressed()
        }
        viewModel.result.observe(this , listObserver)
    }

    fun hideAdd(){
        _binding.icAdd.visibility = View.GONE
    }

    fun showAdd(){
        _binding.icAdd.visibility = View.VISIBLE
    }

    fun hideBack(){
        _binding.icBack.visibility = View.GONE
    }

    fun showBack(){
        _binding.icBack.visibility = View.VISIBLE
    }

    fun changeTitle(title : String){
        _binding.titleText.text = title
    }

    fun getRepoDetail(userName : String , repoName : String){
        viewModel.getUserRepo(userName , repoName)
    }

    fun getRepoList(): ArrayList<RepoResponse> {
       return viewModel.getResult()
    }

    override fun onBackPressed() {
        findNavController(R.id.nav_host_fragment_container).popBackStack(R.id.repositoryListFragment , false)
    }
}