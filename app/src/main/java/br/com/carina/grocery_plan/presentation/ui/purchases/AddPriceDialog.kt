package br.com.carina.grocery_plan.presentation.ui.purchases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.model.SavedPrice
import br.com.carina.grocery_plan.data.state.DialogState
import br.com.carina.grocery_plan.presentation.design.components.CustomTextField
import br.com.carina.grocery_plan.presentation.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.presentation.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.presentation.design.foundation.LSpacing
import br.com.carina.grocery_plan.presentation.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.presentation.design.foundation.XXXLSpacing

@Composable
fun AddPriceDialog(
    state: DialogState<SavedPrice>,
    onConfirm: (Int, String) -> Unit = {_, _ -> },
    onDismiss: () -> Unit = {},
) {
    var price by remember { mutableStateOf("") }

    state.currentDialogData?.let { data ->
        Dialog(
            onDismissRequest = onDismiss,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(size = 16.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 32.dp,
                                bottom = XXXLSpacing,
                                start = XXXLSpacing,
                                end = XXXLSpacing
                            )
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            style = MaterialTheme.typography.headlineMedium,
                            text = if(state.currentDialogData?.price.isNullOrEmpty()) stringResource(
                                R.string.add_price_dialog_add_title
                            ) else stringResource(R.string.add_price_dialog_edit_title),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(XXXLSpacing))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = if(state.currentDialogData?.price.isNullOrEmpty()) stringResource(
                                R.string.add_price_dialog_add_message
                            ) else stringResource(R.string.add_price_dialog_edit_message),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(LSpacing))
                        CustomTextField(
                            label = stringResource(R.string.add_price_dialog_price_label),
                            text = state.currentDialogData?.price ?: "",
                            modifier = Modifier
                                .fillMaxWidth(),
                            isMoney = true,
                            onTextChange = { price = it },
                        )
                        Spacer(modifier = Modifier.size(XXXLSpacing))
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.add_price_dialog_save)
                        ) {
                            state.currentDialogData?.let {
                                onConfirm(it.purchaseId, price)
                            }
                            state.hideDialog()
                        }
                    }

                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            state.hideDialog()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.add_price_dialog_close)
                        )
                    }
                }
            }
        )
    }
}

@LightThemedPreview
@DarkThemedPreview
@Composable
fun AddPriceDialogPreview() {
    val state = DialogState<SavedPrice>()
    state.showDialog(SavedPrice(1, ""))
    GroceryPlanTheme {
        AddPriceDialog(
            state = state,
            onConfirm = {_, _ -> },
            onDismiss = {}
        )
    }
}


