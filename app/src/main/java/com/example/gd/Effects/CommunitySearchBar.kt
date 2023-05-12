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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.gd.ui.theme.SearchBarBD

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommunitySearchBar(onSearch: (String) -> Unit, modifier: Modifier = Modifier) {
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
                    text = "검색"
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
            leadingIcon = {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            trailingIcon = {
                Row {
                    if (searchText != "") {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "clear text",
                            modifier = Modifier
                                .clickable {
                                    searchText = ""
                                }
                                .padding(vertical = 15.dp)
                        )
                    }
                }
            }
        )
    }
}