package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game_over,
                container,
                false)

        //evento do click no botão de tentar novamente
        binding.tryAgainButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
        return binding.root
    }
}
