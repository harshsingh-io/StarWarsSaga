package com.codeenemy.assignmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.codeenemy.assignmentapp.databinding.FilmRowBinding
import com.codeenemy.assignmentapp.models.FilmResponse
/**
 * [FilmsAdapter] is a ListAdapter responsible for displaying a list of Star Wars films.
 * It uses a [DiffUtil.ItemCallback] to efficiently update the UI when the underlying data changes.
 */
class FilmsAdapter :
    ListAdapter<FilmResponse, FilmsAdapter.MyViewHolder>(FILM_COMPARATOR) {

    /**
     * [MyViewHolder] is a RecyclerView ViewHolder class that holds the views for each film item.
     *
     * @param binding The data binding object for the film item layout.
     */
    inner class MyViewHolder(private val binding: FilmRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the film data to the views.
         *
         * @param filmResponse The Star Wars film to be displayed.
         */
        fun bind(filmResponse: FilmResponse?) {
            binding.textViewFilmName.text = filmResponse?.title
            binding.textViewOpeningCrawl.text = filmResponse?.openingCrawl
        }
    }

    /**
     * Inflates the film item layout and creates a new [MyViewHolder] instance.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The type of the view to be created.
     * @return A new instance of [MyViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FilmRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * Binds the film data to the views.
     *
     * @param holder The [MyViewHolder] instance.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film)
    }

    /**
     * [FILM_COMPARATOR] is a [DiffUtil.ItemCallback] for efficiently handling item changes in the list.
     */
    companion object {
        private val FILM_COMPARATOR = object : DiffUtil.ItemCallback<FilmResponse>() {
            override fun areItemsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}
