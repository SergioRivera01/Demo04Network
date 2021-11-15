package com.sergiorivera.demo04network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sergiorivera.demo04network.R

class RepositoryDetailFragment : Fragment() {
    private var repositoryId: String? = null
    private lateinit var tvRepoId: TextView

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
        return inflater.inflate(R.layout.fragment_repository_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvRepoId = view.findViewById(R.id.tv_repo_id)
        tvRepoId.text = repositoryId
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