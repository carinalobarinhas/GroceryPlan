package br.com.carina.grocery_plan.ui.purchases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.carina.grocery_plan.R
import br.com.carina.grocery_plan.data.DialogState
import br.com.carina.grocery_plan.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.design.components.button.SecondaryButton
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.XXXLSpacing

@Composable
fun DeleteCartDialog(
    state: DialogState<Unit>,
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    var savedName by remember { mutableStateOf("") }

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
                            text = "Excluir Carrinho",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(XXXLSpacing))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Deseja realmente excluir o carrinho?",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(XXXLSpacing))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                        ) {
                            SecondaryButton(
                                modifier = Modifier.weight(1f),
                                text = "Cancelar"
                            ) {
                                state.hideDialog()
                            }
                            PrimaryButton(
                                modifier = Modifier.weight(1f),
                                text = "Remover"
                            ) {
                                state.hideDialog()
                                onConfirm(savedName)
                            }
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
                            contentDescription = "Botão de fechar"
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
fun DeleteCartDialogPreview() {
    val state = DialogState<Unit>()
    state.showDialog(Unit)
    GroceryPlanTheme {
        DeleteCartDialog(
            state = state,
            onConfirm = {},
            onDismiss = {}
        )
    }
}


