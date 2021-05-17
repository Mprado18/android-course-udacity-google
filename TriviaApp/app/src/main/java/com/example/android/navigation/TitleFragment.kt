package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_title,
                container,
                false
                )

        //evento de click botão play
        binding.playButton.setOnClickListener { v: View ->
                v.findNavController()
                        .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
            }

        setHasOptionsMenu(true) //adiciona o menu flutuante a este layout
        return binding.root
    }

    //cria menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    //quando item do menu é selecionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}