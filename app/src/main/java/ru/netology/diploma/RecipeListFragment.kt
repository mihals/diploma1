package ru.netology.diploma

//import android.app.SearchManager;
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.diploma.databinding.FragmentRecipeListBinding

//import android.widget.SearchView.OnQueryTextListener;


class RecipeListFragment : Fragment() {
    private val viewModel:RecipeViewModel by activityViewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val bottomBar:BottomAppBar = find
        //(activity as AppCompatActivity).setSupportActionBar(mToolbar)
        viewModel.changeKitchenKindEvent.observe(this) {
            RecipeAdapter(viewModel).notifyDataSetChanged()
//            findNavController().run {
//                popBackStack()
//                navigate(R.id.recipeListFragment)
//                setHasOptionsMenu(false)
//            }
        }
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
            //if( viewModel.selectedKitchenId == 0) {viewModel.selectAll()}
            //else{viewModel.selectedKitchenId ==-1}
            viewModel.data.observe(viewLifecycleOwner) { recipes ->
                //binding.recipeRecyclerView.adapter = adapter
                adapter.submitList(recipes)
            }

            //val searchManager = context?.let { getSystemService(it, Context.SEARCH_SERVICE) }
            binding.fab.setOnClickListener{
                findNavController().navigate(
                    RecipeListFragmentDirections
                        .actionRecipeListFragmentToNewRecipeFragment(-1L)
                )
            }

            setHasOptionsMenu(true)
        }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_view,menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 0
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(context, R.layout.search_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
        val suggestions = arrayOfNulls<String>(KitchenKindEnum.values().size+1)
        suggestions[0] = "Все"
        KitchenKindEnum.values().forEachIndexed{index,element ->
            suggestions[index+1]=element.kitchenKind}

        searchView.suggestionsAdapter = cursorAdapter

        searchItem.setOnActionExpandListener(object:MenuItem
        .OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion != null) {
                            if (suggestion.contains(query, true))
                                cursor.addRow(arrayOf(index, suggestion))
                        }
                    }
                }

                cursorAdapter.changeCursor(cursor)
                val isQueryBlank = query.isNullOrBlank()
                return true
            }
        })

        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {

                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val pos = cursor.position
                viewModel.selectedKitchenId = pos
                val selection = cursor.getString(cursor.getColumnIndexOrThrow(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                // Do something with selection
                context?.let { closeKeyboard(it, view) }
                viewModel.onSelectKitchenKind(pos)
                 return true
            }
        })


        //override fun onActionViewExpanded() {}
        //searchView.onActionViewExpanded()

        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun closeKeyboard(ctx: Context, windowToken: View?) {
            val imm:InputMethodManager = ctx.getSystemService(Activity
                .INPUT_METHOD_SERVICE) as InputMethodManager

            if (windowToken != null) {
                imm.hideSoftInputFromWindow(windowToken.windowToken,0)
            }
        }
    }

}