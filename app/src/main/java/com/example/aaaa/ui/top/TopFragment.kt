package com.example.aaaa.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aaaa.databinding.FragmentTopBinding

class TopFragment : Fragment() {

    private var _binding: FragmentTopBinding? = null

    val topViewModel: TopViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTopBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = topViewModel

        val root: View = binding.root

        val textView: TextView = binding.textHome
        topViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeButton.setOnClickListener {
            topViewModel.getNewGif()
            if (topViewModel.currentItem > 0)
                binding.prevButton.visibility = View.VISIBLE
        }
        binding.prevButton.setOnClickListener {
            topViewModel.getPrevGif()
            if (topViewModel.currentItem == 0)
                binding.prevButton.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}