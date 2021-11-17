package com.sergiorivera.demo04network.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.HttpAuthHandler
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergiorivera.demo04network.R
import com.sergiorivera.demo04network.databinding.FragmentRepositoryListBinding
import com.sergiorivera.demo04network.model.Repository
import com.sergiorivera.demo04network.network.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


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

        requestData()
    }

    private fun requestData() {
        val loggingInterceptor = HttpLoggingInterceptor ()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)
        service.getRepositories().enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Hay un error en la peticion", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Log.e("onFailure", "Failure: ${t.message} ", t)
            }

        })


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