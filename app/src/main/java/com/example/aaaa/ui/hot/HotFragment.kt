package com.example.aaaa.ui.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aaaa.databinding.FragmentHotBinding
import com.example.aaaa.databinding.FragmentLatestBinding

class HotFragment : Fragment() {

    private var _binding: FragmentHotBinding? = null

    val hotViewModel: HotViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHotBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = hotViewModel

        val root: View = binding.root

        val textView: TextView = binding.textHome
        hotViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeButton.setOnClickListener {
            hotViewModel.getNewGif()
            if (hotViewModel.currentItem > 0)
                binding.prevButton.visibility = View.VISIBLE
        }
        binding.prevButton.setOnClickListener {
            hotViewModel.getPrevGif()
            if (hotViewModel.currentItem == 0)
                binding.prevButton.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}