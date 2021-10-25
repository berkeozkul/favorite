package com.example.favoriteteam.features

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.favoriteteam.R
import com.example.favoriteteam.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    private lateinit var _binding: FragmentMainPageBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_fenerbahceHistory)
        }

        binding.fbfotballBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_fenerbahceFootballTeam)


        }

        binding.fbbasketballBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment2_to_fenerbahceBasketballTeam2)

        }
    }

}