package com.example.pokemonfinderapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonfinderapplication.utils.LoadingState

abstract class BaseViewModel: ViewModel() {

    protected val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
}