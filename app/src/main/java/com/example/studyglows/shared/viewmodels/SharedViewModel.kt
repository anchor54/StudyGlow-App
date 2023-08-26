package com.example.studyglows.shared.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.studyglows.screens.auth.common.models.AppUIEvent
import com.example.studyglows.shared.components.drawermenu.BaseDrawerNavigation
import com.example.studyglows.shared.components.drawermenu.MenuItemModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SharedViewModel @AssistedInject constructor(
    @Assisted private val navHostController: NavHostController
): ViewModel() {

    @AssistedFactory
    interface SharedVMAssistedFactory {
        fun create(navHostController: NavHostController): SharedViewModel
    }

    companion object {
        fun providesSharedViewModelFactory(
            factory: SharedVMAssistedFactory,
            navHostController: NavHostController
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
                        return factory.create(navHostController = navHostController) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    private val _drawerInteractions = MutableStateFlow(BaseDrawerNavigation(navHostController))
    val drawerNavigation = _drawerInteractions.asStateFlow()

    private val _drawerMidOptions = MutableStateFlow(listOf<MenuItemModel>())
    val drawerMidOptions = _drawerMidOptions.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AppUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _selectedDrawerOption = MutableStateFlow("")
    val selectedDrawerOption = _selectedDrawerOption.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    fun isLoading(loadingState: Boolean) {
        _isLoading.value = loadingState
    }
    fun showError(error: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _error.emit(error)
        }
    }

    fun setSelectedDrawerOption(itemId: String) {
        _selectedDrawerOption.value = itemId
    }

    fun setDrawerMidOptions(options: List<MenuItemModel>, handler: BaseDrawerNavigation) {
        _drawerMidOptions.value = options
        _drawerInteractions.value = handler
    }

    fun sendUIEvent(event: AppUIEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.emit(event)
        }
    }
}