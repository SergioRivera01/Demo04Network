package com.sergiorivera.demo04network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiorivera.demo04network.R
import com.sergiorivera.demo04network.databinding.FragmentRepositoryListBinding
import com.sergiorivera.demo04network.model.Repository


class RepositoryListFragment : Fragment() {
    private var _binding: FragmentRepositoryListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = RepositoryAdapter {
        val fg = RepositoryDetailFragment.newInstance(it.id)
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.Container, fg)
            .addToBackStack("repository")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRepository.adapter = adapter
        binding.rvRepository.layoutManager = LinearLayoutManager(context)
        binding.rvRepository.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RepositoryListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}