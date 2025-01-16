package com.example.swipetoreveal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SwipeIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color,
    icon: ImageVector,
    contentDescription: String? = null,
    tint: Color = Color.White,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 4.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor.copy(alpha = 0.9f))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
        )
    }

}