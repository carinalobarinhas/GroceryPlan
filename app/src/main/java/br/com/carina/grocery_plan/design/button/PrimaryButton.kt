package br.com.carina.grocery_plan.design.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
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
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        colors = colors
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@br.com.carina.grocery_plan.design.LightThemedPreview
@br.com.carina.grocery_plan.design.DarkThemedPreview
@Composable
fun PrimaryButtonPreview() {
    _root_ide_package_.br.com.carina.grocery_plan.design.GroceryPlanTheme {
        PrimaryButton(
            text = "Criar Carrinho",
            onClick = {}
        )
    }
}