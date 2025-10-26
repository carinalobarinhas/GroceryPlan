package br.com.carina.grocery_plan.presentation.design.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val colors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(56.dp),
        shape = CircleShape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = colors
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun PrimaryButtonPreview() {
    GroceryPlanTheme {
        PrimaryButton(
            text = "Criar Carrinho",
            onClick = {}
        )
    }
}