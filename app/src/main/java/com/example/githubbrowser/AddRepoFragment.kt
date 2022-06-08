package com.example.githubbrowser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.githubbrowser.databinding.FragmentAddRepoBinding

class AddRepoFragment : Fragment() {

    private lateinit var _binding : FragmentAddRepoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        (activity as MainActivity).showBack()
        (activity as MainActivity).changeTitle("Add Repo")

        _binding = FragmentAddRepoBinding.inflate(layoutInflater)

        _binding.addRepoButton.setOnClickListener {
            if(_binding.ownerInput.text.toString() == "" || _binding.repoNameInput.text.toString() == "" ){
                Toast.makeText(requireContext(), "Owner or Repo Name cannot be empty!!", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            (activity as MainActivity).getRepoDetail(_binding.ownerInput.text.toString() , _binding.repoNameInput.text.toString())
        }

        return _binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideBack()
    }

}