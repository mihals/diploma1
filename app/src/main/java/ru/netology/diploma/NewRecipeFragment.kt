package ru.netology.diploma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.diploma.databinding.FragmentNewRecipeBinding

class NewRecipeFragment : Fragment() {
    private val args  by navArgs<SingleRecipeFragmentArgs>()
    private val viewModel:RecipeViewModel by activityViewModels ()
    private lateinit var recipe:Recipe


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(args.recipeId!=-1L){
            recipe=viewModel.getById(args.recipeId)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=  FragmentNewRecipeBinding.inflate(layoutInflater,container,false)
        .also{binding ->
            val kitchenKindArray = arrayOfNulls<String>(KitchenKindEnum.values().size)
            KitchenKindEnum.values().forEachIndexed{index,element ->
            kitchenKindArray[index]=element.kitchenKind}

            val adapter = ArrayAdapter(getActivity()!!.baseContext,
                android.R.layout.simple_spinner_item,kitchenKindArray)
            binding.spinnerKitchen.adapter = adapter
            if(args.recipeId!=-1L){
                binding.spinnerKitchen.setSelection(recipe.kitchenOrdinal)
            }

            binding.spinnerKitchen.onItemSelectedListener = object:AdapterView
            .OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.selectedKitchenKindItem = binding.spinnerKitchen
                        .selectedItemPosition
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            if((::recipe.isInitialized)){
                binding.editCaption.setText(recipe.recipeName)
                binding.editContent.setText(recipe.content)
            }

            binding.fab.setOnClickListener {
                if(args.recipeId==-1L) {
                    viewModel.onAddNewRecipe(
                        Recipe(
                            0,
                            viewModel.selectedKitchenKindItem,
                            binding.editCaption.text.toString(),
                            binding.editContent.text.toString()
                        )
                    )
                    viewModel.selectedKitchenKindItem = 0
                }
                else{
                    viewModel.update(Recipe(
                        recipe.id,
                        viewModel.selectedKitchenKindItem,
                        binding.editCaption.text.toString(),
                        binding.editContent.text.toString()
                    ))
                }
                findNavController().popBackStack()
            }
        }.root


    companion object {
    }
}