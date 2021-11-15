package com.sergiorivera.demo04network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sergiorivera.demo04network.R
import com.sergiorivera.demo04network.databinding.FragmentRepositoryDetailBinding

class RepositoryDetailFragment : Fragment() {
    private var repositoryId: String? = null
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repositoryId = it.getString(ARG_REPOSITORY_ID)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRepoId.text = repositoryId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_REPOSITORY_ID = "ar_repository_id"

        @JvmStatic
        fun newInstance(repositoryId: String) =
            RepositoryDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_REPOSITORY_ID, repositoryId)
                }
            }
    }
}