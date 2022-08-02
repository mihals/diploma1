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
        listener:RecipeViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var recipe: Recipe

        init {
            itemView.setOnClickListener {
                listener.onRecipeCardClicked(recipe.id)
            }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe

            with(binding) {
                kitchenName.text = KitchenKindEnum
                    .values()[recipe.kitchenOrdinal].kitchenKind
                recipeContent.text =recipe.content
                recipeCaption.text = recipe.recipeName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeBinding.inflate(inflater,parent,false)
        return ViewHolder(binding,interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int
    ) {
        holder.bind(getItem(position))
    }

    private object DiffCallBack : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe)=
            oldItem == newItem
    }
}