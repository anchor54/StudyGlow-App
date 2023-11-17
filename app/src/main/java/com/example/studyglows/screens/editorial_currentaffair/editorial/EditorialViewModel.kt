package com.example.studyglows.screens.editorial_currentaffair.editorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.EditorialApis
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialDetails
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import com.example.studyglows.shared.model.SearchResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorialViewModel @Inject constructor(
    private val editorialApis: EditorialApis
): ViewModel() {
    private val _editorials = MutableStateFlow(listOf<EditorialItem>())
    val editorials = _editorials.asStateFlow()

    private val _editorial_details = MutableStateFlow(EditorialDetails())
    val editorial_details = _editorial_details.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchResultItem>())
    val searchResult = _searchResults.asStateFlow()

    fun getEditorialList() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = editorialApis.getEditorialList()
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _editorials.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getEditorialDetails(editorialId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = editorialApis.getEditorialDetails(editorialId)
            _loading.value = false
            if (response.isSuccessful) {
                response.body()?.let {
                    _editorial_details.value = it
                }
            } else {
                _error.emit(response.message() ?: "Something went wrong")
            }
        }
    }

    fun getSearchResults(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = editorialApis.search(searchText)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchResults.value = it
                }
            }
        }
    }
}