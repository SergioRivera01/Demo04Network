package com.sergiorivera.demo04network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sergiorivera.demo04network.R


class RepositoryListFragment : Fragment() {
    private lateinit var tvClickme : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvClickme = view.findViewById(R.id.tv_click_me)

        val fg = RepositoryDetailFragment.newInstance("123")
        tvClickme.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.Container, fg ).setReorderingAllowed(true).addToBackStack("reposiroty").commit()
        }
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