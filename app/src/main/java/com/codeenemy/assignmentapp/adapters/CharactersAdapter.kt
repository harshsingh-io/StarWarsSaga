package com.codeenemy.assignmentapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeenemy.assignmentapp.databinding.CharactersRowBinding
import com.codeenemy.assignmentapp.models.Character
/**
 * [CharactersAdapter] displays a paginated list of Star Wars characters using the Paging library.
 * It utilizes a [DiffUtil.ItemCallback] for efficient updates.
 *
 * @param onClickListener: Handles character item clicks.
 */
class CharactersAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<Character, CharactersAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: CharactersRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character?) {
            // Binds character data to views
            binding.tvName.text = character?.name
            binding.tvDob.text = character?.birthYear
            binding.tvGender.text = character?.gender
            binding.tvHeight.text = character?.height
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflates the character item layout and creates a new ViewHolder
        return MyViewHolder(
            CharactersRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Binds character data to views and sets up click listener
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(character!!)
        }
    }


    companion object {
        // [DiffUtil.ItemCallback] for efficient item updates
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
    // Click listener class
    class OnClickListener(val clickListener: (character: Character) -> Unit) {
        fun onClick(character: Character) = clickListener(character)
    }
}