package com.codeenemy.assignmentapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codeenemy.assignmentapp.databinding.FilmRowBinding
import com.codeenemy.assignmentapp.models.FilmResponse

class FilmsAdapter :
    ListAdapter<FilmResponse, FilmsAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: FilmRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmResponse: FilmResponse?) {
            binding.textViewFilmName.text = filmResponse?.title
            binding.textViewOpeningCrawl.text = filmResponse?.openingCrawl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FilmRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<FilmResponse>() {
            override fun areItemsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}