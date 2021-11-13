package dev.nmgalo.trukha.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.databinding.CharacterItemBinding
import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel

class CharactersRecyclerAdapter :
    ListAdapter<CharactersUIModel, CharactersRecyclerAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position) bindTo CharacterItemBinding.bind(holder.itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DiffUtilCallback : DiffUtil.ItemCallback<CharactersUIModel>() {
        override fun areItemsTheSame(
            oldItem: CharactersUIModel,
            newItem: CharactersUIModel
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CharactersUIModel,
            newItem: CharactersUIModel
        ) = oldItem.id == newItem.id
    }

}
