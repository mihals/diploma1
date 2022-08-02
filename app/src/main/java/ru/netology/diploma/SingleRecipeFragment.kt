package ru.netology.diploma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.diploma.databinding.FragmentSingleRecipeBinding


class SingleRecipeFragment : Fragment() {
    private val args  by navArgs<SingleRecipeFragmentArgs>()
    private val viewModel:RecipeViewModel by activityViewModels ()
    private lateinit var recipe:Recipe

    private val popupMenu by lazy{
        PopupMenu(//activity!!.baseContext,
            context,
            view?.findViewById(R.id.options)
        ).apply {
            inflate(R.menu.options)
            setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.remove ->{
                        viewModel.onRemoveRecipeClicked(recipe)
                        findNavController().popBackStack()
                    }
                    R.id.edit ->{
                        findNavController().navigate(
                            SingleRecipeFragmentDirections
                                .actionSingleRecipeFragmentToNewRecipeFragment(
                                    recipe.id))
                    }
                    R.id.toList ->{
                        findNavController().popBackStack()
                    }
                }
                true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //recipe = viewModel.getById(args.recipeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentSingleRecipeBinding.inflate(layoutInflater,container,false)
        .also{binding ->
            binding.options.setOnClickListener{popupMenu.show()}
            recipe = viewModel.getById(args.recipeId)
            binding.textContent.text=recipe.content
            binding.textCaption.text=recipe.recipeName
            binding.kitchenKind.text =
                KitchenKindEnum.values()[recipe.kitchenOrdinal].kitchenKind
        }.root
        //return inflater.inflate(R.layout.fragment_single_recipe, container, false)
}