package com.example.favoriteteam.features.football

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favoriteteam.adapters.PlayersAdapter
import com.example.favoriteteam.adapters.OnPlayersClickListener
import com.example.favoriteteam.databinding.FragmentFootballTeamBinding
import com.example.favoriteteam.instance
import com.example.favoriteteam.service.Players

class FootballTeamFragment : Fragment(),
    OnPlayersClickListener {

    private lateinit var viewModel: FootballTeamViewModel
    private lateinit var footballAdapter: PlayersAdapter

    companion object {
        private val TAG = "FootballTeamFragment"
    }

    private lateinit var _binding: FragmentFootballTeamBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(FootballTeamViewModel::class.java)
        footballAdapter = PlayersAdapter(requireContext(), mutableListOf<Players>(), this)

        _binding = FragmentFootballTeamBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //listenin linearlayutunu ayarlıyoruz adaptoru bağlıyoruz.
        binding.footballrcycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = footballAdapter
        }

        // geri döne butonunu tnaımlıyoruz ne yapması gerektiğini tanımıyoruz.

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        addObservers()

        viewModel.openDbZone()
    }

    private fun addObservers() {
        //Live Data’da meydana gelen herhangi bir değişikliği, Observer sayesinde kolay bir şekilde gözlemleyebiliyoruz.
        viewModel.playersListLiveData!!.observe(viewLifecycleOwner, Observer {
            footballAdapter.updatePlayers(it)
        })
    }

    //Oyuncuya tıkladığımız zaman  ne yapılması gerektiğini tanımlıyoruz.
    override fun onItemClick(item: Players) {
        val bundle = Bundle()
        bundle.putString("playername",item.name)
        instance.onEvent("onplayerclick", bundle)
        val action =
            FootballTeamFragmentDirections.actionFenerbahceFootballTeamToFootballCard(item.id)
        findNavController().navigate(action)
    }

}