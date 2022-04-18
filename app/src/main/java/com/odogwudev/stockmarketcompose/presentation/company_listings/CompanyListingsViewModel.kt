package com.odogwudev.stockmarketcompose.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odogwudev.stockmarketcompose.domain.repository.StockRepository
import com.odogwudev.stockmarketcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    var state by mutableStateOf(CompanyListingsState())
    private var searchjob: Job? = null

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is CompanyListingsEvent.Refresh -> {
                getCOmpanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.query)
                searchjob?.cancel()
                searchjob = viewModelScope.launch {
                    delay(500L)
                    getCOmpanyListings()
                }
            }
        }
    }

    fun getCOmpanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getCompanyListings(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings //avoid race conditions
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            ("Couldn't Load the dat IO Exception ")
                        }
                    }
                }
        }
    }
}