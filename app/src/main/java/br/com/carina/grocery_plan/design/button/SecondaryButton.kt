package br.com.carina.grocery_plan.design.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.carina.grocery_plan.design.GroceryPlanTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    onClick: () -> Unit,
) {
    val colors = ButtonDefaults.buttonColors().copy(
        containerColor = Transparent,
        contentColor = MaterialTheme.colorScheme.primary
    )

    val border = BorderStroke(
        width = 0.5.dp,
        color = MaterialTheme.colorScheme.primary
    )

    // TODO Setup colors when it is setup in the theme
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(56.dp),
        colors = colors,
        shape = shape,
        border = border,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
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
fun SecondaryButtonPreview() {
    GroceryPlanTheme {
        SecondaryButton(
            text = "Criar Carrinho",
            onClick = {}
        )
    }
}