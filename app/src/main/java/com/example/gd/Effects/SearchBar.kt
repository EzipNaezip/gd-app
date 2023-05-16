package com.example.gd.Effects

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.gd.Button.ClearTextButton
import com.example.gd.ui.theme.SearchBarBD

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit, modifier: Modifier = Modifier) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column() {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    color = MaterialTheme.colors.secondaryVariant,
                    text = "원하시는 스타일을 입력해주세요"
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .border(
                    width = 1.dp,
                    color = SearchBarBD,
                    shape = RoundedCornerShape(8.dp)
                ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    search(searchText, onSearch, focusManager, keyboardController)
                }),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Row {
                    if (searchText != "") {
                        ClearTextButton {
                            searchText = ""
                        }
                    }
                    Divider(
                        color = SearchBarBD,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )

                    TextButton(
                        onClick = {
                            search(searchText, onSearch, focusManager, keyboardController)
                        },
                    ) {
                        Text(
                            text = "생성",
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun search(
    searchText: String,
    onSearch: (String) -> Unit,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
) {
    if (searchText.isNotEmpty()) {
        onSearch(searchText)
    }
    focusManager.clearFocus()
    keyboardController?.hide()
}