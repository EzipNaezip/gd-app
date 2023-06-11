package com.dongminpark.projectgd.Effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.ui.theme.SearchBarBD

@Composable
fun SearchBarShimmer(onClick: () -> Unit){
    // Shimmer 기본 설정값들
    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(69.dp)
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .border(
                width = 1.dp,
                color = SearchBarBD,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(brush)
        )
        Row(
            Modifier.background(MaterialTheme.colors.onSecondary)
        ) {
            Divider(
                color = SearchBarBD,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            TextButton(
                onClick = {
                          onClick()
                    // Stop Generate구현
                          // state 변경
                },
            ) {
                Text(
                    text = "중단",
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}