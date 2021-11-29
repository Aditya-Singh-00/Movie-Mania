package com.aditya.moviemania.ui.screen.search

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviemania.R
import com.aditya.moviemania.domain.repository.MovieRepository
import com.aditya.moviemania.util.MovieManiaException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val application: Application
) : ViewModel() {

    private val _searchScreenState: MutableState<SearchScreenState> =
        mutableStateOf(SearchScreenState.Passive)
    val searchScreenState: State<SearchScreenState> = _searchScreenState

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun onSearchQueryChange(query: String) {
        if (_searchScreenState.value == SearchScreenState.Error()) {
            _searchScreenState.value = SearchScreenState.Passive
        }
        _searchQuery.value = query
    }

    fun getSearchResults() {
        val query = searchQuery.value.trim()
        if (query.isBlank()) {
            _searchScreenState.value = SearchScreenState.Error(
                application.applicationContext
                    .resources.getString(R.string.query_blank)
            )
            return
        }
        viewModelScope.launch {
            _searchScreenState.value = SearchScreenState.Loading
            try {
                _searchScreenState.value =
                    SearchScreenState.Success(repository.getSearchResults(query))
            } catch (e: MovieManiaException) {
                _searchScreenState.value = SearchScreenState.Error(e.localizedMessage)
            }
        }
    }
}