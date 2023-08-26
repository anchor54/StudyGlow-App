package com.example.studyglows.screens.editorial_currentaffair.current_affairs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.CurrentAffairApis
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairItem
import com.example.studyglows.screens.editorial_currentaffair.current_affairs.model.CurrentAffairDetails
import com.example.studyglows.shared.model.CategoryFilter
import com.example.studyglows.utils.Utils.replace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentAffairsViewModel @Inject constructor(
    private val currentAffairApis: CurrentAffairApis
): ViewModel() {
    private val _currentAffairs = MutableStateFlow(listOf<CurrentAffairItem>())
    val currentAffairs = _currentAffairs.asStateFlow()

    private val _currentAffairDetails = MutableStateFlow(CurrentAffairDetails())
    val currentAffairDetails = _currentAffairDetails.asStateFlow()

    private val _filters = MutableStateFlow(listOf<CategoryFilter>())
    val filters = _filters.asStateFlow()

    private val _selectedFilters = MutableStateFlow<List<CategoryFilter>>(listOf())
    val selectedFilters = _selectedFilters.asStateFlow()

    fun getAllCurrentAffairs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currentAffairApis.fetchAllCurrentAffairs()
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairs.value = it
                }
            }
        }
    }

    fun addFilter(category: String, filterText: String) {
        val categoryFilter = _selectedFilters.value.find { it.filterCategory == category }
        val newFilterList = (categoryFilter?.filterFields ?: listOf()) + filterText

        _selectedFilters.value =
            if(categoryFilter == null)
                _selectedFilters.value + listOf(CategoryFilter(category, newFilterList))
            else _selectedFilters.value.replace(
                CategoryFilter(category, newFilterList)
            ) { it.filterCategory == category }
    }

    fun removeFilter(category: String, filterText: String) {
        val categoryFilter = _selectedFilters.value.find { it.filterCategory == category }
        val newFilterList = (categoryFilter?.filterFields ?: listOf()) - filterText
        _selectedFilters.value = _selectedFilters.value.replace(
            CategoryFilter(category, newFilterList)
        ) { it.filterCategory == category }
    }

    fun clearFilterCategory(category: String) {
        _selectedFilters.value = _selectedFilters.value.replace(
            CategoryFilter(category, listOf())
        ) { it.filterCategory == category }
    }

    fun getAllCategoryFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currentAffairApis.getAllCategoryFilters()
            if (response.isSuccessful) {
                response.body()?.let {
                    _filters.value = listOf(
                        CategoryFilter(
                            filterCategory = "CURRENT_AFFAIRS",
                            filterFields = it
                        )
                    )
                }
            }
        }
    }

    fun applyFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currentAffairApis.filterCourses(_selectedFilters.value[0].filterFields)
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairs.value = it
                }
            }
        }
    }

    fun getCurrentAffairDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currentAffairApis.fetchCurrentAffairDetails(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    _currentAffairDetails.value = it
                }
            }
        }
    }
}