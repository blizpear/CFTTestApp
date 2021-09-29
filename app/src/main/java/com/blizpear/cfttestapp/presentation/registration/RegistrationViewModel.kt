package com.blizpear.cfttestapp.presentation.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blizpear.cfttestapp.domain.model.PersonData
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _name = MutableLiveData<String>("")
    private val _surname = MutableLiveData("")
    private val _passwd = MutableLiveData("")
    private val _rePasswd = MutableLiveData("")
    private val _correctPasswd = MutableLiveData(false)
    private val _date = MutableLiveData("")

    lateinit var personData: PersonData

    fun onNameChanged(text: CharSequence?) {
        if (text.isNullOrEmpty())
            _name.value = ""
        else
            if (_name.value != text) {
                _name.value = text.toString()
                Timber.d("${_name.value}")
            }
    }

    fun onSurnameChanged(text: CharSequence?) {
        if (text.isNullOrEmpty())
            _surname.value = ""
        else
            if (_surname.value != text) {
                _surname.value = text.toString()
                Timber.i("${_surname.value}")
            }
    }

    fun onPasswdChanged(text: CharSequence?) {
        if (text.isNullOrEmpty())
            _passwd.value = ""
        else
            if (_passwd.value != text) {
                _passwd.value = text.toString()
                Timber.i("${_passwd.value}")
            }
        passwIsCorrect()
    }

    fun onRepasswdChanged(text: CharSequence?) {
        if (text.isNullOrEmpty())
            _rePasswd.value = ""
        else
            if (_rePasswd.value != text) {
                _rePasswd.value = text.toString()
                Timber.i("${_rePasswd.value}")
            }
        passwIsCorrect()
    }

    fun onDateChanged(text: CharSequence?) {
        if (text.isNullOrEmpty())
            _date.value = ""
        else
            if (_date.value != text) {
                _date.value = text.toString()
                Timber.i("${_date.value}")
            }
    }

    fun allFieldsNotEmptyAndCorrect(): Boolean {
        return _name.value !== "" &&
                _passwd.value !== "" &&
                _rePasswd.value !== "" &&
                _surname.value !== "" &&
                _date.value !== "" &&
                _correctPasswd.value == true
    }

    fun onButtonClicked() {
        personData = PersonData(_name.value!!, _surname.value!!, _passwd.value!!, _date.value!!)
    }

    private fun passwIsCorrect() {
        if (_passwd.value == _rePasswd.value)
            _correctPasswd.value = true
    }
}