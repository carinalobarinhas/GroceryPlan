package br.com.carina.grocery_plan.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: String,
    text: String,
    enabled: Boolean = true,
    onTextChange: (String) -> Unit = {},
) {
    val textState = remember { mutableStateOf(text) }

    val colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
    )

    LaunchedEffect(text) {
        if (text != textState.value) {
            textState.value = text
        }
    }

    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onTextChange(it)
        },
        enabled = enabled,
        label = { Text(label) },
        colors = colors
    )
}

@LightThemedPreview
@DarkThemedPreview
@Composable
private fun TextFieldPreview() {
    GroceryPlanTheme {
        CustomTextField(
            label = "Nome do Mercado",
            text = "",
            onTextChange = { },
        )
    }
}