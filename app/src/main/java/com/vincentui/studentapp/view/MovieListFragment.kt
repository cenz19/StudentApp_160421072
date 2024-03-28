package com.vincentui.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincentui.studentapp.R
import com.vincentui.studentapp.databinding.FragmentMovieListBinding
import com.vincentui.studentapp.databinding.StudentListItemBinding
import com.vincentui.studentapp.viewmodel.ListViewModel
import com.vincentui.studentapp.viewmodel.ListViewModelMovie


class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: ListViewModelMovie
    private val movieListAdapter  = MovieListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModelMovie::class.java)
        viewModel.refresh()

        binding.recViewMovie.layoutManager = LinearLayoutManager(context)
        binding.recViewMovie.adapter = movieListAdapter

        observeViewModel();

        binding.refreshLayoutMovie.setOnRefreshListener {
            binding.recViewMovie.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayoutMovie.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.movieLD.observe(viewLifecycleOwner, Observer {
            movieListAdapter.updateMovieList(it)
        })

        viewModel.movieLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recViewMovie.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewMovie.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

    }
}