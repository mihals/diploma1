package ru.netology.diploma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.netology.diploma.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    private val viewModel:RecipeViewModel by activityViewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        .also { binding ->
            val adapter = RecipeAdapter(viewModel)
            binding.favoriteRecyclerView.adapter = adapter
            viewModel.selectFavorite()
            viewModel.data.observe(viewLifecycleOwner) { recipes ->
                //binding.favoriteRecyclerView.adapter = adapter
                adapter.submitList(recipes)
            }
        }.root
}