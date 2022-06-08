package com.example.githubbrowser

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepoRVAdapter( private val context : Context, private val listener : IRepoRVAdapter) :RecyclerView.Adapter<RepoRVAdapter.RepoViewHolder>(){

    private var allRepos = ArrayList<RepoResponse>()

    inner class RepoViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        val repoName : TextView = itemView.findViewById(R.id.repo_name)
        val repoDesc : TextView = itemView.findViewById(R.id.repo_desc)
        val icSend : ImageButton = itemView.findViewById(R.id.ic_send_repo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoRVAdapter.RepoViewHolder {
        val viewHolder = RepoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repo_rv , parent , false))
        viewHolder.icSend.setOnClickListener {
            listener.onShareClicked(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RepoRVAdapter.RepoViewHolder, position: Int) {
        val currentRepo = allRepos[position]
        holder.repoName.text = currentRepo.name
        if(currentRepo.description!=null)
            holder.repoDesc.text = currentRepo.description
        else
            holder.repoDesc.text = "No Description Avaialable"
    }

    override fun getItemCount(): Int {
        return allRepos.size
    }

    fun updateRepos(newList : ArrayList<RepoResponse>){
        allRepos = newList
        notifyDataSetChanged()
    }

}

interface IRepoRVAdapter{
    fun onShareClicked(position : Int)
}