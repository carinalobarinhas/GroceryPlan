package br.com.carina.grocery_plan.ui.products

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
import androidx.compose.runtime.mutableIntStateOf
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
import br.com.carina.grocery_plan.design.components.CustomTextField
import br.com.carina.grocery_plan.design.components.button.PrimaryButton
import br.com.carina.grocery_plan.design.foundation.DarkThemedPreview
import br.com.carina.grocery_plan.design.foundation.GroceryPlanTheme
import br.com.carina.grocery_plan.design.foundation.LSpacing
import br.com.carina.grocery_plan.design.foundation.LightThemedPreview
import br.com.carina.grocery_plan.design.foundation.SSpacing
import br.com.carina.grocery_plan.design.foundation.XXXLSpacing

@Composable
fun NewProductDialog(
    state: DialogState<Unit>,
    onConfirm: (String, Int) -> Unit = {_, _ -> },
    onDismiss: () -> Unit = {},
) {
    var savedName by remember { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(0) }

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
                            text = "Novo Produto",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(XXXLSpacing))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Adicione o nome do produto e quantidade de items",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(LSpacing))
                        CustomTextField(
                            label = "Nome do mercado",
                            modifier = Modifier
                                .fillMaxWidth(),
                            onTextChange = { savedName = it },
                        )

                        Spacer(modifier = Modifier.size(LSpacing))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                onClick = {
                                    quantity = quantity - 1
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_decrement),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = "Diminuir quantidade"
                                )
                            }

                            Spacer(modifier = Modifier.padding(horizontal = SSpacing))
                            Text(
                                text = quantity.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.padding(horizontal = SSpacing))
                            IconButton(
                                onClick = {
                                    quantity = quantity + 1
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_increment),
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = "Aumentar quantidade"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(LSpacing))
                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Salvar Carrinho"
                        ) {
                            state.hideDialog()
                            onConfirm(savedName, quantity)
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
fun NewProductDialogPreview() {
    val state = DialogState<Unit>()
    state.showDialog(Unit)
    GroceryPlanTheme {
        NewProductDialog(
            state = state,
            onConfirm = {_, _ -> },
            onDismiss = {}
        )
    }
}


