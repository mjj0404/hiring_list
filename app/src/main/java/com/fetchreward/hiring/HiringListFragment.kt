package com.fetchreward.hiring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fetchreward.hiring.databinding.FragmentMainBinding
import com.fetchreward.hiring.model.HiringItem

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
        viewModel = ViewModelProvider(this).get(HiringListViewModel::class.java)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}