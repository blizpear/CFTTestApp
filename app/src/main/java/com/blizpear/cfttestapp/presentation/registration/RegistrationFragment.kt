package com.blizpear.cfttestapp.presentation.registration

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blizpear.cfttestapp.R
import com.blizpear.cfttestapp.databinding.RegistrationFragmentBinding
import java.util.*

class RegistrationFragment : Fragment() {

    private lateinit var binding: RegistrationFragmentBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistrationFragmentBinding.inflate(inflater)

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
                binding.surnameInputLayout.error =
                    context?.resources?.getString(R.string.surname_error)
            } else {
                binding.surnameInputLayout.isErrorEnabled = false
                binding.surnameInputLayout.error = null
            }
        }

        binding.passwdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswdChanged(text)

            if (viewModel.correctPasswdStatus.value == false) {
                binding.passwdInputLayout.isErrorEnabled = true
                binding.passwdInputLayout.error =
                    context?.resources?.getString(R.string.passwd_error)
            } else {
                binding.passwdInputLayout.isErrorEnabled = false
                binding.passwdInputLayout.error = null
                binding.repasswdInputLayout.isErrorEnabled = false
                binding.repasswdInputLayout.error = null
            }
        }

        binding.rePasswdEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onRepasswdChanged(text)

            if (viewModel.correctPasswdStatus.value == false) {
                binding.repasswdInputLayout.isErrorEnabled = true
                binding.repasswdInputLayout.error =
                    context?.resources?.getString(R.string.repasswd_error)
            } else {
                binding.repasswdInputLayout.isErrorEnabled = false
                binding.repasswdInputLayout.error = null
                binding.passwdInputLayout.isErrorEnabled = false
                binding.passwdInputLayout.error = null
            }
        }

        binding.registrationButton.setOnClickListener {
            viewModel.onButtonClicked()
            findNavController().navigate(
                R.id.action_registrationFragment_to_greetingsFragment,
                bundleOf("PersonData" to viewModel.personData)
            )
        }

        binding.dateButton.setOnClickListener {
            callDatePicker()
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

        return binding.root
    }

    private fun callDatePicker() {
        val dpd = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                viewModel.onDateChanged(dayOfMonth, monthOfYear, year)
                binding.dateButton.text = "$dayOfMonth-$monthOfYear-$year"

            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        dpd.show()
    }
}