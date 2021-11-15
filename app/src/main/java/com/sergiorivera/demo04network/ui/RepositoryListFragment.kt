package com.sergiorivera.demo04network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sergiorivera.demo04network.R
import com.sergiorivera.demo04network.databinding.FragmentRepositoryListBinding


class RepositoryListFragment : Fragment() {
    private var _binding: FragmentRepositoryListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fg = RepositoryDetailFragment.newInstance("123")
        binding.tvClickMe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.Container, fg)
                .setReorderingAllowed(true)
                .addToBackStack("repository")
                .commit()
        }
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