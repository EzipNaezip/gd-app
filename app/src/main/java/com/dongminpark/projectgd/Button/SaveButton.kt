package com.dongminpark.projectgd.Button

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE


@Composable
fun SaveButton(num: Int?, saved: Boolean = false, onClick: () -> Unit) {
    var isSaved by rememberSaveable { mutableStateOf(saved) }

    Icon(
        painter = if (isSaved) painterResource(id = R.drawable.save) else painterResource(id = R.drawable.save_black),
        contentDescription = "Save",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                Log.e(TAG, "SaveButton: $isSaved")
                if (isSaved) {
                    RetrofitManager.instance.postDelete(
                        num,
                        completion = { responseState ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    Log.d(TAG, "포스트 삭제 api 호출 성공")
                                    Toast
                                        .makeText(App.instance, MESSAGE.DELETE, Toast.LENGTH_SHORT)
                                        .show()
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Log.d(TAG, "포스트 삭제 api 호출 에러")
                                    Toast
                                        .makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        })
                } else {
                    RetrofitManager.instance.gptStore(
                        num,
                        completion = { responseState ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    Log.d(TAG, "포스트 저장 api 호출 성공")
                                    Toast
                                        .makeText(App.instance, MESSAGE.SAVE, Toast.LENGTH_SHORT)
                                        .show()
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Log.d(TAG, "포스트 저장 api 호출 에러")
                                    Toast
                                        .makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        })
                }
                isSaved = !isSaved
            },
        tint = Color.Unspecified
    )
}