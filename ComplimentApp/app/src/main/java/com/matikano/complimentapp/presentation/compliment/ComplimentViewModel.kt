package com.matikano.complimentapp.presentation.compliment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matikano.complimentapp.domain.compliment.Compliment
import com.matikano.complimentapp.domain.repository.ComplimentRepository
import com.matikano.complimentapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComplimentViewModel @Inject constructor(
    private val repository: ComplimentRepository
): ViewModel() {

    var state by mutableStateOf(ComplimentState())
        private set


    fun onEvent(event: ComplimentEvent){
        when (event){
            is ComplimentEvent.OnRefresh -> if (!state.isRefreshing) loadCompliment()
            is ComplimentEvent.OnLoadCompliment -> loadCompliment(event.content)
        }
    }

    private fun loadCompliment(
        content: String? = null
    ){
        if(content != null) {
            state = state.copy(
                compliment = Compliment(content),
                isLoading = false,
                error = null
            )
        } else {
            viewModelScope.launch {
                state = state.copy(
                    isLoading = true,
                    error = null
                )
                when (val result = repository.getCompliment()){
                    is Resource.Success -> {
                        state = state.copy(
                            compliment = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            compliment = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}