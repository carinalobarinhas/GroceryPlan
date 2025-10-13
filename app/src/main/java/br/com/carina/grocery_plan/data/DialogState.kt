package br.com.carina.grocery_plan.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DialogState<T> {
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
