package br.com.carina.grocery_plan.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.MSpacing
import br.com.carina.grocery_plan.design.foundation.SSpacing
import br.com.carina.grocery_plan.design.foundation.ToolbarHeight
import br.com.carina.grocery_plan.design.foundation.XXLSpacing

@Composable
fun CustomToolbar(
    title: String = "",
    hasBackButton: Boolean = true,
) {
    val leftPadding = if(hasBackButton) MSpacing else XXLSpacing
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToolbarHeight)
            .padding(start = leftPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SSpacing)
    ) {
        if(hasBackButton) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                tint = Color.Black,
                contentDescription = "Botão de voltar"
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun CustomToolbarWithBackPreview() {
    GroceryPlanTheme {
        CustomToolbar(
            title = "Grocery Plan"
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun CustomToolbarWithoutBackPreview() {
    GroceryPlanTheme {
        CustomToolbar(
            title = "Grocery Plan",
            hasBackButton = false
        )
    }
}