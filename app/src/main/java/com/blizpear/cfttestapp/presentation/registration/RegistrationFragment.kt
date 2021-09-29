package com.blizpear.cfttestapp.presentation.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blizpear.cfttestapp.R
import com.blizpear.cfttestapp.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RegistrationFragmentBinding.inflate(inflater)

        binding.nameTextField.doOnTextChanged { text, _, _, _ ->
            viewModel.onNameChanged(text ?: "")
        }
        binding.surnameEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onSurnameChanged(text ?: "")
        }
        binding.passwdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswdChanged(text)
        }
        binding.rePasswdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onRepasswdChanged(text)
        }
        binding.dateEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onDateChanged(text)
        }

        binding.registrationButton.setOnClickListener {
            if (viewModel.allFieldsNotEmptyAndCorrect()) {
                viewModel.onButtonClicked()
                findNavController().navigate(
                    R.id.action_registrationFragment_to_greetingsFragment,
                    bundleOf("PersonData" to viewModel.personData)
                )
            }
        }
        return binding.root
    }
}