package com.example.citypulse

import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun RatingBarView(
    rating: Float,
    onRatingChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RatingBar(context).apply {
                numStars = 5
                stepSize = 1f
            }
        },
        update = { bar ->
            bar.onRatingBarChangeListener = null // évite un écho
            bar.rating = rating
            bar.contentDescription = "Note : ${rating.toInt()} sur 5"
            bar.setOnRatingBarChangeListener { _, value, fromUser ->
                if (fromUser) onRatingChange(value)
            }
        }
    )
}
