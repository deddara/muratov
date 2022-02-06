package com.example.aaaa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aaaa.databinding.FragmentLatestBinding

class LatestFragment : Fragment() {

    private var _binding: FragmentLatestBinding? = null

    val homeViewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel

        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeButton.setOnClickListener {
            homeViewModel.getNewGif()
            if (homeViewModel.currentItem > 0)
                binding.prevButton.visibility = View.VISIBLE
        }
        binding.prevButton.setOnClickListener {
            homeViewModel.getPrevGif()
            if (homeViewModel.currentItem == 0)
                binding.prevButton.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}