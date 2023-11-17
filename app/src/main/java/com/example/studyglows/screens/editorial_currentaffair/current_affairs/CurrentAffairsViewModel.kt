package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.CurrentAffairApis
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairDetails
import com.example.studyglows.shared.model.CategorizedList
import com.example.studyglows.shared.model.CategoryFilter
import com.example.studyglows.shared.model.SearchResultItem
import com.example.studyglows.utils.Utils.replace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentAffairsViewModel @Inject constructor(
    private val currentAffairApis: CurrentAffairApis
): ViewModel() {
    val FILTER_CATEGORY = "CURRENT_AFFAIRS"
    private val _currentAffairs = MutableStateFlow(listOf<CurrentAffairItem>())
    val currentAffairs = _currentAffairs.asStateFlow()

    private val _currentAffairDetails = MutableStateFlow(CurrentAffairDetails())
    val currentAffairDetails = _currentAffairDetails.asStateFlow()

    private val _filters = MutableStateFlow(CategorizedList<String>())
    val filters = _filters.asStateFlow()

    private val _selectedFilters = MutableStateFlow(CategorizedList<String>())
    val selectedFilters = _selectedFilters.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchResultItem>())
    val searchResult = _searchResults.asStateFlow()

    fun getAllCurrentAffairs() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = currentAffairApis.fetchAllCurrentAffairs()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairs.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun removeFilter(category: String, filterText: String) {
        _selectedFilters.value = _selectedFilters.value.removeItem(category, filterText)
    }

    fun getAllCategoryFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = currentAffairApis.getAllCategoryFilters()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _filters.value = CategorizedList(mapOf(Pair(FILTER_CATEGORY, it)))
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun applyFilters(filters: CategorizedList<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = currentAffairApis.filterCurrentAffairs(filters.getListForCategory(FILTER_CATEGORY))
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairs.value = it
                }
                _selectedFilters.value = filters
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun applyDateFilter(date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = currentAffairApis.filterCoursesByDate(date)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairs.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getCurrentAffairDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = currentAffairApis.fetchCurrentAffairDetails(id)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairDetails.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getSearchResults(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currentAffairApis.search(searchText)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchResults.value = it
                }
            }
        }
    }
}