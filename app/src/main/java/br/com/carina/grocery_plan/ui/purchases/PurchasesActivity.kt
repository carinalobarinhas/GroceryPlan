package br.com.carina.grocery_plan.ui.purchases

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme

class PurchasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GroceryPlanTheme {
                PurchasesView()
            }
        }
    }
}