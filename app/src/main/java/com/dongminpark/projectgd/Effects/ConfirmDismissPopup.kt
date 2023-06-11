package com.dongminpark.projectgd.Effects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.dongminpark.projectgd.ui.theme.suite

@Composable
fun ConfirmDismissPopupFormat(
    titleText: String,
    dialogText: String,
    buttonText: String,
    buttonColor: Color,
    runButtonClick: () -> Unit,
    dismissButtonClick: () -> Unit,
    ifDoubleButton: Boolean,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            shape = RoundedCornerShape(12.dp),
            title = {
                Text(
                    text = titleText,
                    fontFamily = suite,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            },
            text = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dialogText,
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.secondary
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { runButtonClick() },
                    modifier = Modifier
                        .width(100.dp)
                        .padding(8.dp)
                ) {
                    Text(
                        text = buttonText,
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = buttonColor
                    )
                }
            },
            dismissButton = {
                if (ifDoubleButton) {
                    TextButton(
                        onClick = { dismissButtonClick() },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "취소",
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            },
            properties = DialogProperties(dismissOnClickOutside = true)
        )
    }
}