package com.fetchreward.hiring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fetchreward.hiring.databinding.FragmentMainBinding

class HiringListFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HiringListFragment()
    }

    private lateinit var viewModel: HiringListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HiringListViewModel::class.java)
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        val root: View = binding.root

        val textView: TextView = binding.message
        textView.text = "QEWEQ"

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}