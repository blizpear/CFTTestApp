package com.blizpear.cfttestapp.presentation.registration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
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

            if (viewModel.nameStatus.value == false) {
                binding.nameInputLayout.isErrorEnabled = true
                binding.nameInputLayout.error = context?.resources?.getString(R.string.name_error)
            } else {
                binding.nameInputLayout.isErrorEnabled = false
                binding.nameInputLayout.error = null
            }
        }

        binding.surnameEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onSurnameChanged(text ?: "")

            if (viewModel.surnameStatus.value == false) {
                binding.surnameInputLayout.isErrorEnabled = true
                binding.surnameInputLayout.error = context?.resources?.getString(R.string.surname_error)
            } else {
                binding.surnameInputLayout.isErrorEnabled = false
                binding.surnameInputLayout.error = null
            }
        }

        binding.passwdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswdChanged(text)

            if (viewModel.correctPasswdStatus.value == false) {
                binding.passwdInputLayout.isErrorEnabled = true
                binding.passwdInputLayout.error = context?.resources?.getString(R.string.passwd_error)
            } else {
                binding.passwdInputLayout.isErrorEnabled = false
                binding.passwdInputLayout.error = null
            }
        }

        binding.rePasswdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onRepasswdChanged(text)

            if (viewModel.correctPasswdStatus.value == false) {
                binding.repasswdInputLayout.isErrorEnabled = true
                binding.repasswdInputLayout.error = context?.resources?.getString(R.string.repasswd_error)
            } else {
                binding.repasswdInputLayout.isErrorEnabled = false
                binding.repasswdInputLayout.error = null
            }
        }

        binding.dateEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onDateChanged(text)

            if (viewModel.dateStatus.value == false) {
                binding.dateInputLayout.isErrorEnabled = true
                binding.dateInputLayout.error = context?.resources?.getString(R.string.date_error)
            } else {
                binding.dateInputLayout.isErrorEnabled = false
                binding.dateInputLayout.error = null
            }
        }

        binding.registrationButton.setOnClickListener {
            viewModel.onButtonClicked()
            findNavController().navigate(
                R.id.action_registrationFragment_to_greetingsFragment,
                bundleOf("PersonData" to viewModel.personData)
            )
        }


        viewModel.statusAllFields.observe(viewLifecycleOwner, {
            if (it) {
                binding.registrationButton.isActivated = true
                binding.registrationButton.setBackgroundColor(Color.BLUE)
                binding.registrationButton.isClickable = true
            } else {
                binding.registrationButton.isActivated = false
                binding.registrationButton.setBackgroundColor(Color.GRAY)
                binding.registrationButton.isClickable = false
            }
        })

//        viewModel.nameStatus.observe(viewLifecycleOwner, {
//            if (!it) {
//                binding.textInputLayout.error = R.string.name_error.toString()
//            } else binding.textInputLayout.isErrorEnabled = false
//        })
//        viewModel.surnameStatus.observe(viewLifecycleOwner, {
//
//        })
//        viewModel.dateStatus.observe(viewLifecycleOwner, {
//
//        })
//        viewModel.correctPasswdStatus.observe(viewLifecycleOwner, {
//
//        })
        return binding.root
    }
}