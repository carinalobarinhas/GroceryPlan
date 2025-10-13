package br.com.zeenow.zeenow.v3_clean_code.core.ui.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ZeeDialogState<T> {
    var currentDialogData by mutableStateOf<T?>(null)

    val isDialogVisible: Boolean
        get() = currentDialogData != null

    fun updateState(state: T?) {
        currentDialogData = state
    }

    fun showDialog(
        data: T
    ) {
        currentDialogData = data
    }

    fun hideDialog() {
        currentDialogData = null
    }
}
