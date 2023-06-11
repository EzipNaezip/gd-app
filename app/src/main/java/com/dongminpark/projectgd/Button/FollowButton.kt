package com.dongminpark.projectgd.Button

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.Constants
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE


@Composable
fun FollowButton(isUserPage: Boolean = false, followingId: Int, isFollow: Boolean) {
    var isFollowing by remember { mutableStateOf(isFollow) }

    val buttonColors = if (isFollowing) {
        ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    } else {
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
    }

    OutlinedButton(
        onClick = {
            if (isFollowing){
                RetrofitManager.instance.followFollowingFollowingId(
                    followingId,
                    completion = { responseState ->
                        when (responseState) {
                            RESPONSE_STATE.OKAY -> {
                                isFollowing = !isFollowing
                            }
                            RESPONSE_STATE.FAIL -> {
                                Toast
                                    .makeText(
                                        com.dongminpark.projectgd.App.instance,
                                        MESSAGE.ERROR,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                Log.d(Constants.TAG, "MainScreen: examples load Error")
                            }
                        }
                    })
            }else{
                RetrofitManager.instance.followUnfollowingFollowerId(
                    followingId,
                    completion = { responseState ->
                        when (responseState) {
                            RESPONSE_STATE.OKAY -> {
                                isFollowing = !isFollowing
                            }
                            RESPONSE_STATE.FAIL -> {
                                Toast
                                    .makeText(
                                        App.instance,
                                        MESSAGE.ERROR,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                Log.d(Constants.TAG, "MainScreen: examples load Error")
                            }
                        }
                    })
            } },
        colors = buttonColors,
        modifier = if (isUserPage){
            Modifier
                .padding(start = 0.dp, end = 0.dp)
                .fillMaxWidth()
        } else{
            Modifier.size(80.dp, 40.dp)
              },
        shape = RoundedCornerShape(30)
    ) {
        Text(
            text = if (isFollowing) "팔로잉" else "팔로우",
            color = if (isFollowing) Color.Black else Color.White,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
        )
    }
}

