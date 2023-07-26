package com.example.studyglows.utils

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.studyglows.utils.Constants.RUPEE

object Utils {
    fun amountWithRupeeSymbol(amount: Float = 0f, showPaise: Boolean = true) =
        if (showPaise) "$RUPEE${roundOffDecimal(amount)}"
        else "$RUPEE${amount.toInt()}"

    private fun roundOffDecimal(number: Float): String = "%.2f".format(number)

    fun getDiscountedPriceText(
        originalPrice: String,
        discountedPrice: String = "",
        strikeThroughSize: TextUnit = 15.sp,
        normalSize: TextUnit = 17.sp
    ) = buildAnnotatedString {
        if (discountedPrice.isNotEmpty()) {
            pushStyle(
                SpanStyle(
                    textDecoration = TextDecoration.LineThrough,
                    fontSize = strikeThroughSize
                )
            )
            append(originalPrice)
            pop()
            append("  ")
            pushStyle(SpanStyle(fontSize = normalSize))
            append(discountedPrice)
            pop()
        } else {
            pushStyle(SpanStyle(fontSize = normalSize))
            append(originalPrice)
            pop()
        }
    }

    fun getFormattedDurationString(seconds: Long): String {
        val minutes = seconds / 60
        val secs = seconds % 60
        return "$minutes Minutes $secs Seconds"
    }
}