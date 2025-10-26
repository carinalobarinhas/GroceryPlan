package br.com.carina.grocery_plan.presentation.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: String,
    text: String = "",
    enabled: Boolean = true,
    isMoney: Boolean = false,
    onTextChange: (String) -> Unit = {},
) {
    val colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.secondary
    )

    val brFormatter = remember {
        val locale = Locale.Builder()
            .setLanguage("pt")
            .setRegion("BR")
            .build()
        NumberFormat.getCurrencyInstance(locale).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }
    }

    // Local formatter helpers
    fun formatDigitsAsBRL(digits: String): String {
        if (digits.isEmpty()) return ""
        val cents = digits.toLongOrNull() ?: return ""
        return brFormatter.format(cents / 100.0)
    }

    // State as TextFieldValue to control selection
    var tfv by remember {
        val initial = if (isMoney) {
            val digits = text.filter { it.isDigit() }
            val formatted = formatDigitsAsBRL(digits)
            TextFieldValue(formatted, TextRange(formatted.length))
        } else {
            TextFieldValue(text, TextRange(text.length))
        }
        mutableStateOf(initial)
    }

    // Keep state in sync if external `text` changes
    LaunchedEffect(text, isMoney) {
        val desired = if (isMoney) {
            val digits = text.filter { it.isDigit() }
            formatDigitsAsBRL(digits)
        } else text

        if (tfv.text != desired) {
            tfv = TextFieldValue(desired, TextRange(desired.length)) // caret at end
        }
    }

    OutlinedTextField(
        value = tfv,
        onValueChange = { newValue ->
            if (isMoney) {
                // Rebuild from digits and force cursor to the end
                val digits = newValue.text.filter { it.isDigit() }
                val formatted = formatDigitsAsBRL(digits)
                tfv = TextFieldValue(formatted, TextRange(formatted.length))
                onTextChange(formatted)
            } else {
                tfv = newValue
                onTextChange(newValue.text)
            }
        },
        enabled = enabled,
        label = { Text(label) },
        colors = colors,
        keyboardOptions = KeyboardOptions(
            capitalization = if (isMoney) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
            keyboardType = if (isMoney) KeyboardType.Number else KeyboardType.Text
        ),
        modifier = modifier
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