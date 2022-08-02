package ru.netology.diploma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.diploma.databinding.FragmentRecipeListBinding


class RecipeListFragment : Fragment() {
    private val viewModel:RecipeViewModel by activityViewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigateToSingleRecipeFragmentEvent
            .observe(this){recipeId ->
                findNavController().navigate(RecipeListFragmentDirections
                    .actionRecipeListFragmentToSingleRecipeFragment(recipeId))
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecipeListBinding.inflate(layoutInflater,container,false)
        .also { binding ->
            val adapter = RecipeAdapter(viewModel)
            binding.recipeRecyclerView.adapter = adapter
            viewModel.data.observe(viewLifecycleOwner) { recipes ->
                adapter.submitList(recipes)
            }
            //binding.itemSearch.set
            binding.fab.setOnClickListener{
                findNavController().navigate(
                    RecipeListFragmentDirections
                        .actionRecipeListFragmentToNewRecipeFragment(-1L)
                )
            }
        }.root
}