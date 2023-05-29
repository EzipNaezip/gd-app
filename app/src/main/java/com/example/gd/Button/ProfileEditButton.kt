package com.example.gd.Button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.theme.suite

@Composable
fun ProfileEditButton(onClick: () -> Unit){
    OutlinedButton(
        modifier = Modifier
            .padding(start = 0.dp, end = 0.dp)
            .fillMaxWidth(),
        onClick = { onClick() },
        border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant),
        shape = RoundedCornerShape(30)
    ) {
        Text(
            text = "프로필 수정",
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onPrimary
        )
    }
}