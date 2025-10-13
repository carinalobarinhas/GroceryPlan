package br.com.zeenow.zeenow.v3_clean_code.core.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.zeenow.zeenow.R
import br.com.zeenow.zeenow.v3_clean_code.core.annotation.ThemedPreview
import br.com.zeenow.zeenow.v3_clean_code.core.ui.states.ZeeConfirmationDialogData
import br.com.zeenow.zeenow.v3_clean_code.core.ui.states.ZeeDialogState
import br.com.zeenow.zeenow.v3_clean_code.core.ui.theme.ZnBlack
import br.com.zeenow.zeenow.v3_clean_code.core.ui.theme.ZnWhite

@Composable
fun ZeeConfirmationDialog(
    state: ZeeDialogState<ZeeConfirmationDialogData>,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    content: (@Composable () -> Unit)? = null
) {
    state.currentDialogData?.let { data ->
        Dialog(
            onDismissRequest = onDismiss,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(size = 16.dp))
                        .background(ZnWhite),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                    ) {
                        Heading1(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            text = data.title.asString(),
                            textAlign = TextAlign.Center
                        )
                        VerticalSpacer(size = 16.dp)
                        if (content != null) {
                            content()
                        } else {
                            Paragraph(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                text = data.text.asString(),
                                textAlign = TextAlign.Center
                            )
                        }
                        VerticalSpacer(size = 16.dp)
                        Row(modifier = Modifier.fillMaxWidth()) {
                            data.dismissButton?.let {
                                ZeeButton(
                                    modifier = Modifier.weight(1f),
                                    title = data.dismissButton.asString(),
                                    type = ButtonType.POPUP_HALF_LEFT,
                                    style = ButtonStyle.SECONDARY_POP_UP,
                                    onClick = onDismiss
                                )
                            }
                            ZeeButton(
                                modifier = Modifier.weight(1f),
                                title = data.confirmButton.asString(),
                                type = if (data.dismissButton == null) {
                                    ButtonType.POPUP
                                } else {
                                    ButtonType.POPUP_HALF_RIGHT
                                },
                                style = data.buttonStyle,
                                onClick = {
                                    onConfirm()
                                    onDismiss()
                                }
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { state.hideDialog() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                            contentDescription = "",
                            tint = ZnBlack
                        )
                    }
                }
            }
        )
    }
}

@ThemedPreview
@Composable
fun ZeeConfirmationDialogPreview() {
    val state = ZeeDialogState<ZeeConfirmationDialogData>()
    state.showDialog(ZeeConfirmationDialogData.DeleteConfirmation)
    ZeeConfirmationDialog(
        state = state,
        onConfirm = {},
        onDismiss = {}
    )
}


