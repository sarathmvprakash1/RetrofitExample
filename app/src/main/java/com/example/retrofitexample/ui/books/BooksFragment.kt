package com.example.retrofitexample.ui.books

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofitexample.R
import com.example.retrofitexample.databinding.FragmentBooksBinding
import com.example.retrofitexample.databinding.FragmentLoginBinding
import com.example.retrofitexample.login.LogInViewModel

class BooksFragment : Fragment() {
    private var _binding: FragmentBooksBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = BooksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.e("BooksFragment", "onCreateView")
        val recyclerView = binding.recycleView
        val adapter = MarsRealEstateAdapter(MarsRealEstateAdapter.OnClickListener {
            Log.e("BooksFragment", "onCreateView: OnClickListener: ")
        })
        recyclerView.adapter = adapter

        viewModel.getBookList()
        viewModel.bookList.observe(viewLifecycleOwner) {
            Log.e("BooksFragment", "onCreateView: SIZE: "+it.size)
            adapter.submitList(it)
            //recyclerView.adapter = adapter
        }
        return root
    }
}