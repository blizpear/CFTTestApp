package com.blizpear.cfttestapp.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blizpear.cfttestapp.domain.model.PersonData
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val MIN_LETTER_NAME = 2
        const val MIN_LETTER_PASSWD = 6
        const val MIN_DATE = "01-12-1902"
    }

    private val _name = MutableLiveData("")
    private val _surname = MutableLiveData("")
    private val _passwd = MutableLiveData("")
    private val _rePasswd = MutableLiveData("")
    private val _date = MutableLiveData<String>()

    private val _nameStatus = MutableLiveData<Boolean>(false)
    private val _surnameStatus = MutableLiveData<Boolean>(false)
    private val _correctPasswdStatus = MutableLiveData<Boolean>(false)
    private val _dateStatus = MutableLiveData<Boolean>(false)

    val nameStatus: LiveData<Boolean> = _nameStatus
    val surnameStatus: LiveData<Boolean> = _surnameStatus
    val correctPasswdStatus: LiveData<Boolean> = _correctPasswdStatus
    val dateStatus: LiveData<Boolean> = _dateStatus

    val statusAllFields = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(_name) { value = allFieldsNotEmptyAndCorrect() }
        addSource(_surname) { value = allFieldsNotEmptyAndCorrect() }
        addSource(_correctPasswdStatus) { value = allFieldsNotEmptyAndCorrect() }
        addSource(_date) { value = allFieldsNotEmptyAndCorrect() }
    }

    lateinit var personData: PersonData

    fun onNameChanged(text: CharSequence?) {
        if (_name.value != text) {
            _name.value = text.toString()
            nameIsCorrect()
            Timber.d("${_name.value}")
        }
    }

    fun onSurnameChanged(text: CharSequence?) {
        if (_surname.value != text) {
            _surname.value = text.toString()
            surnameIsCorrect()
            Timber.i("${_surname.value}")
        }
    }

    fun onPasswdChanged(text: CharSequence?) {
        if (_passwd.value != text) {
            _passwd.value = text.toString()
            Timber.i("${_passwd.value}")
        }
        passwdIsCorrect()
    }

    fun onRepasswdChanged(text: CharSequence?) {
        if (_rePasswd.value != text) {
            _rePasswd.value = text.toString()
            Timber.i("${_rePasswd.value}")
        }
        passwdIsCorrect()
    }

    fun onDateChanged(text: CharSequence?) {
        if (_date.value != text) {
            _date.value = text.toString()
            dateIsCorrect()
            Timber.i("${_date.value}")
        }
    }

    fun onButtonClicked() {
        personData = PersonData(_name.value!!, _surname.value!!, _passwd.value!!, _date.value!!)
    }

    private fun passwdIsCorrect() {
        _correctPasswdStatus.value =
            (_passwd.value == _rePasswd.value)
                    && _passwd.value!!.contains("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])".toRegex())
                    && _passwd.value?.length!! >= MIN_LETTER_PASSWD
    }

    private fun nameIsCorrect() {
        _nameStatus.value = _name.value?.length!! >= MIN_LETTER_NAME
    }

    private fun surnameIsCorrect() {
        _surnameStatus.value = _surname.value?.length!! >= MIN_LETTER_NAME
    }

    private fun dateIsCorrect() {
        if (_date.value?.length!! < 10) {
            _dateStatus.value = false
        } else {
            val formatter = DateTimeFormatter.ofPattern("d-MM-yyyy", Locale.ENGLISH)
            val dateTmp = LocalDate.parse(_date.value, formatter)
            if (dateTmp > LocalDate.parse(MIN_DATE, formatter))
                _dateStatus.value = true
        }
    }

    private fun allFieldsNotEmpty(): Boolean {
        return !_name.value.isNullOrBlank() &&
                !_passwd.value.isNullOrBlank() &&
                !_rePasswd.value.isNullOrBlank() &&
                !_surname.value.isNullOrBlank() &&
                !_date.value.isNullOrBlank() &&
                !_passwd.value.isNullOrBlank() &&
                !_rePasswd.value.isNullOrBlank()
    }

    private fun allFieldsNotEmptyAndCorrect(): Boolean {
        return allFieldsNotEmpty()
                && _correctPasswdStatus.value!!
                && _nameStatus.value!!
                && _surnameStatus.value!!
                && _dateStatus.value!!
    }
}