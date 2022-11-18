package com.example.translationapp.ui.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translationapp.R
import com.example.translationapp.data.repository.TranslationRepo
import com.example.translationapp.utilities.Resource
import com.example.translationapp.utilities.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    private val translationRepo: TranslationRepo
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<String>>(Resource.Empty())
    val state: StateFlow<Resource<String>> = _state

    fun getTranslation(translator: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = Resource.Loading()

                val translation = translationRepo.getTranslation(translator, text)
                _state.value = Resource.Success(translation.contents.translated)
            } catch(e: Exception) {
                _state.value = Resource.Failure(UiText.StringResource(resId = R.string.error))
            }
        }
    }
}
