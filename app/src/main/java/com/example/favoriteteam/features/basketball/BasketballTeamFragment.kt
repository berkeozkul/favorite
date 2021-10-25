package com.example.favoriteteam.features.basketball

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoriteteam.adapters.OnPlayersClickListener
import com.example.favoriteteam.adapters.PlayersAdapter
import com.example.favoriteteam.databinding.FragmentBasketballTeamBinding
import com.example.favoriteteam.instance
import com.example.favoriteteam.service.Players

class BasketballTeamFragment : Fragment(),
    OnPlayersClickListener {

    private lateinit var viewModel: BasketballTeamViewModel
    private lateinit var basketballAdapter: PlayersAdapter

    companion object {
        private val TAG = "BasketballTeamFragment"
    }

    private lateinit var _binding: FragmentBasketballTeamBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBasketballTeamBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(BasketballTeamViewModel::class.java)

        basketballAdapter = PlayersAdapter(requireContext(), mutableListOf(), this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.basketballrcycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = basketballAdapter
        }


        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        addObservers()
        viewModel.openDbZone()
    }

    private fun addObservers() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, { players ->
            basketballAdapter.updatePlayers(players)
        })
    }

    override fun onItemClick(item: Players) {
        val bundle = Bundle()
        bundle.putString("playername",item.name)
        instance.onEvent("onplayerclick", bundle)
        val action =
            BasketballTeamFragmentDirections.actionFenerbahceBasketballTeamToPlayerCard(item.id)
        findNavController().navigate(action)
    }
}