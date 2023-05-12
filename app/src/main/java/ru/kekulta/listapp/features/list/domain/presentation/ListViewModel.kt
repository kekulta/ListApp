package ru.kekulta.listapp.features.list.domain.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.kekulta.listapp.features.list.data.dto.ArtSearchRequest
import ru.kekulta.listapp.features.list.domain.models.ListState
import ru.kekulta.listapp.features.list.domain.api.ArtInteractor
import ru.kekulta.listapp.features.list.domain.models.ListScreenState
import ru.kekulta.listapp.shared.models.ArtItem
import ru.kekulta.listapp.shared.models.Resource

class ListViewModel(private val interactor: ArtInteractor) : ViewModel() {

    private var collector: Job? = null
    private val _state: MutableStateFlow<ListScreenState> = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> get() = _state
    private var query: String = ""

    fun nextPage() {
        _state.value = _state.value.copy(listState = ListState.Loading)
        searchArts(query, _state.value.paginationState.current + 1)
    }

    fun prevPage() {
        _state.value = _state.value.copy(listState = ListState.Loading)
        searchArts(query, _state.value.paginationState.current - 1)
    }

    fun searchQuery(query: String) {
        this.query = query
        _state.value = _state.value.copy(listState = ListState.Loading)
        searchArts(query, 1)
    }

    private fun searchArts(query: String, page: Int) {
        collector?.cancel()
        collector = viewModelScope.launch {
            interactor.searchArts(ArtSearchRequest.QueryRequest(query, page)).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        Log.d(LOG_TAG, "new state, ${response.data.arts}")
                        _state.value =
                            ListScreenState(
                                listState = ListState.ShowList(),
                                paginationState = response.data.paginationState,
                                list = response.data.arts,
                            )
                    }

                    is Resource.Error -> {
                        _state.value = ListScreenState(ListState.Error(response.message))
                    }

                }
            }
        }
    }


    companion object {
        const val LOG_TAG = "ListViewModel"
    }
}