package com.dongminpark.projectgd.Button

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.Utils.Constants
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE


@Composable
fun BookmarkButton(bookmark: Boolean, postNum: Int) {
    var isBookmark by rememberSaveable { mutableStateOf(bookmark) }

    Icon(
        painter = if (isBookmark) painterResource(id = R.drawable.bookmark_color) else painterResource(
            id = R.drawable.bookmark_outline
        ),
        contentDescription = "Bookmark",
        modifier = Modifier
            .size(26.dp)
            .clickable {
                if (isBookmark) {
                    RetrofitManager.instance.bookmarksDeletePostNum(
                        postNum, completion = { responseState ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    isBookmark = !isBookmark
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Toast.makeText(
                                        App.instance,
                                        MESSAGE.ERROR,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d(Constants.TAG, "Bookmark Delete Error")
                                }
                            }
                        })
                } else {
                    RetrofitManager.instance.bookmarksCreatePostNum(
                        postNum, completion = { responseState ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    isBookmark = !isBookmark
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Toast
                                        .makeText(
                                            App.instance,
                                            MESSAGE.ERROR,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    Log.d(Constants.TAG, "Bookmark Create Error")
                                }
                            }
                        })
                }
            },
        tint = Color.Unspecified
    )
}