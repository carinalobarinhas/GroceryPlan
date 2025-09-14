package br.com.carina.grocery_plan.design.foundation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", backgroundColor = 0xFFEFEFEF, showBackground = true)
annotation class LightThemedPreview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", backgroundColor = 0xFF000000, showBackground = true)
annotation class DarkThemedPreview