package com.dongminpark.projectgd.Effects

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.USER

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldFormat(fieldTitle: String) {
    var userValue by remember { mutableStateOf(if (fieldTitle == "이름") USER.NAME else USER.DESCRIPTION) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isTextFieldFocused = false

    Column(modifier = Modifier
        .padding(8.dp)
        .addFocusCleaner(focusManager)
    ) {
        Text(
            text = fieldTitle,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = MaterialTheme.colors.secondary
        )
        TextField(
            value = userValue,
            onValueChange = {
                userValue = it
                if (fieldTitle == "이름") USER.NAMETEMP = it
                else USER.DESCRIPTION_TEMP = it
                Log.e(TAG, "TextFieldFormat: ${USER.NAMETEMP}, ${USER.DESCRIPTION_TEMP}", )
                            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged {
                    isTextFieldFocused = it.isFocused
                },
            textStyle = TextStyle(
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Divider(
            color = MaterialTheme.colors.secondary,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}