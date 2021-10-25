package com.example.favoriteteam.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.favoriteteam.PlayerCardViewModel
import com.example.favoriteteam.R
import com.example.favoriteteam.databinding.FragmentPlayerCardBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerCardBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: PlayerCardViewModel

    private val args: PlayerCardBottomSheetDialogFragmentArgs by navArgs()

    private lateinit var _binding: FragmentPlayerCardBottomSheetBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(PlayerCardViewModel::class.java)

        _binding = FragmentPlayerCardBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    //Full sayfa gelmesini sağlıyorum bottomsheetin
    override fun onStart() {
        super.onStart()
        val view: FrameLayout = dialog?.findViewById(R.id.design_bottom_sheet)!!
        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        val behavior = BottomSheetBehavior.from(view)
        behavior.peekHeight = 3000
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()

        viewModel.openCloudDBZone(args.playerId)
    }

    private fun addObservers() {
        viewModel.playerCardData.observe(viewLifecycleOwner, { player ->
            binding.name.text = player.name
            binding.power.text = player.power
            binding.shoot.text = player.shoot
            binding.pass.text = player.pass
            binding.phy.text = player.phy
            binding.dribling.text = player.dribling
            binding.def.text = player.defence
            binding.pace.text = player.pace
            Glide.with(requireContext()).load(player.country).into(binding.country);
            Glide.with(requireContext()).load(player.team).into(binding.team);
            Glide.with(requireContext()).load(player.playerphoto).into(binding.player);
        })
    }

}