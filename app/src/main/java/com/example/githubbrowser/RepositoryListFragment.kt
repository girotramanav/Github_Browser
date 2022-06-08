package com.example.githubbrowser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.databinding.FragmentRepositoryListBinding

class RepositoryListFragment : Fragment() , IRepoRVAdapter {

    private lateinit var _binding : FragmentRepositoryListBinding
    private lateinit var viewModel : AddRepoViewModel
    private lateinit var adapter : RepoRVAdapter
    private var repoList = ArrayList<RepoResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryListBinding.inflate(layoutInflater)

        (activity as MainActivity).changeTitle("Github Browser")
        (activity as MainActivity).showAdd()
        viewModel = ViewModelProvider(this)[AddRepoViewModel::class.java]
        adapter = RepoRVAdapter(requireContext() , this)
        Log.d("MyTAG" , "Calling RV")
        _binding.repoRecyclerView.layoutManager = LinearLayoutManager(context)
        _binding.repoRecyclerView.adapter = adapter
        _binding.addRepoButton.setOnClickListener {
            findNavController().navigate(R.id.action_repositoryListFragment_to_addRepoFragment)
        }

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showAdd()
        Log.d("MyTAG" , repoList.size.toString())
        repoList = (activity as MainActivity).getRepoList()
        if(repoList.size>0)
        {
            _binding.trackLayout.visibility = View.GONE
            _binding.repoRecyclerView.visibility = View.VISIBLE
            Log.d("MyTAG" , repoList[0].toString())
        }
        adapter.updateRepos(repoList)
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).hideAdd()
    }

    override fun onShareClicked(position: Int) {
        Log.d("MyTAG" , repoList[position].html_url)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "I am sharing this GitHub Repo with you. " + repoList[position].html_url + " Do Check It Out!!")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent , null)
        startActivity(shareIntent)
    }
}