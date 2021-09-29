package com.blizpear.cfttestapp.presentation.greetings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.blizpear.cfttestapp.R
import com.blizpear.cfttestapp.databinding.GreetingsFragmentBinding
import com.blizpear.cfttestapp.databinding.RegistrationFragmentBinding
import com.blizpear.cfttestapp.domain.model.PersonData

class GreetingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = GreetingsFragmentBinding.inflate(inflater)

        val personDate = arguments?.get("PersonData") as PersonData

        val viewModel: GreetingsViewModel by viewModels()

        binding.greetingButton.setOnClickListener {
            Toast.makeText(requireContext(),
            "Привет, ${personDate.surname} ${personDate.name}",
            Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

}