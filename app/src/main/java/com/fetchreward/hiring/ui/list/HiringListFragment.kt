package com.fetchreward.hiring.ui.list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fetchreward.hiring.HiringListViewModel
import com.fetchreward.hiring.R
import com.fetchreward.hiring.databinding.FragmentMainBinding
import com.fetchreward.hiring.model.HiringItem
import com.fetchreward.hiring.ui.setting.ThemeSelectionFragment

class HiringListFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HiringListFragment()
    }

    private lateinit var viewModel: HiringListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        viewModel = ViewModelProvider(this)[HiringListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HiringListAdapter {hiringItem: HiringItem ->
            Toast.makeText(
                requireContext(),
                requireActivity().getString(
                    R.string.onClickToast,
                    hiringItem.id.toString(),
                    hiringItem.listId.toString(),
                    hiringItem.name),
                Toast.LENGTH_SHORT
            ).show()
            //TODO: possibly move to other fragment
        }

        binding.hiringListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.hiringListRecyclerview.adapter = adapter


        viewModel.hiringItemList.observe(viewLifecycleOwner) {
            adapter.setHiringList(it)
        }
        viewModel.hiringItemListFailed.observe(viewLifecycleOwner) {
            // TODO: set Loading
            if (it) {
                // TODO
            }
            else {
                // TODO
            }
        }
        viewModel.fetchItems()

        requireActivity().addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_fragment_main, menu)
                val searchItem = menu.findItem(R.id.menu_item_search)
                val searchView: SearchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(p0: String?): Boolean {
                        if (p0.isNullOrBlank())
                            adapter.setSearchFilter("")
                        else
                            adapter.setSearchFilter(p0)
                        return false
                    }
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_item_theme) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ThemeSelectionFragment.newInstance())
                        .commitNow()
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}