package com.dongminpark.projectgd.Effects

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongminpark.projectgd.ui.theme.suite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopAppBarScreenFormat(
    titleText: String,
    IsLeftButton: Boolean,
    IsRightButton: Boolean,
    content: @Composable () -> Unit,
    leftButtonClick: () -> Unit,
    rightButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = MaterialTheme.colors.onPrimary,
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (IsLeftButton) {
                            IconButton(
                                onClick = { leftButtonClick() }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back Button",
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        } else {
                            IconButton(onClick = { }, enabled = false) {}
                        }
                        Text(
                            text = titleText,
                            fontFamily = suite,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                        if (IsRightButton) {
                            IconButton(
                                onClick = { rightButtonClick() },
                                modifier = Modifier.padding(end = 14.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Save Button",
                                    tint = MaterialTheme.colors.primaryVariant
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { },
                                modifier = Modifier.padding(end = 14.dp),
                                enabled = false
                            ) {}
                        }
                    }
                }
            )
        }
    ) {
        content()
    }
}