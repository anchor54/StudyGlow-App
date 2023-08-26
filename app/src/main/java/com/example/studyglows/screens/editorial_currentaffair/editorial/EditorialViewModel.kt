package com.example.studyglows.screens.editorial_currentaffair.editorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyglows.network.apis.EditorialApis
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialDetails
import com.example.studyglows.screens.editorial_currentaffair.editorial.model.EditorialItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun getEditorialList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = editorialApis.getEditorialList()
            if (response.isSuccessful) {
                response.body()?.let {
                    _editorials.value = it
                }
            }
        }
    }

    fun getEditorialDetails(editorialId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = editorialApis.getEditorialDetails(editorialId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _editorial_details.value = it
                }
            }
        }
    }
}