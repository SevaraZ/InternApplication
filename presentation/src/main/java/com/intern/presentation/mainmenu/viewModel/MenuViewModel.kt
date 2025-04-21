package com.intern.presentation.mainmenu.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.data.managers.DataStoreManger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    private val dataStoreManger = DataStoreManger(context)

    val themeModeState: Flow<Boolean> = dataStoreManger.getSettings().stateIn(
        viewModelScope,
        SharingStarted.Companion.Lazily,
        false
    )

    fun saveSettings(themeMode: Boolean) {
        viewModelScope.launch {
            dataStoreManger.saveTheme(themeMode)
        }
    }
}