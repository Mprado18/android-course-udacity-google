package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game_won,
                container,
                false)

        //evento de click no botão de jogar novamente
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        //exibindo menu
        setHasOptionsMenu(true)

//        Toast.makeText(context,
//                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
//                Toast.LENGTH_LONG).show()

        return binding.root
    }

    //exibindo menu compartilhamento
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu.findItem(R.id.share)?.isVisible = (false)
        }
    }

    //cria ação de seleção de item no menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    //cria intenção de compartilhamento
    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(
                        Intent.EXTRA_TEXT,
                        getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent
    }

    //realiza compartilhamento com sucesso
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
