package com.zakia.idn.androidtrivianavigations.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.zakia.idn.androidtrivianavigations.R
import com.zakia.idn.androidtrivianavigations.databinding.FragmentGameOverBinding

/**
 * A simple [Fragment] subclass.
 */
class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_over, container, false
        )
        binding.btnTryAgain.setOnClickListener { view: View ->
            view.findNavController().navigate(
                R.id.action_gameOverFragment_to_gameFragment
            )
        }
        return binding.root
    }
}
