package ru.netology.diploma

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.diploma.databinding.RecipeBinding

internal class RecipeAdapter(
    private val interactionListener:RecipeViewModel)
    : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(
        private val binding: RecipeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: Recipe

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            with(binding) {
                kitchenName.text = recipe.kithenName
                recipeContent.text =recipe.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int
    ) {
        holder.bind(getItem(position))
        //holder.
    }

    private object DiffCallBack : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe)=
            oldItem == newItem
    }
}