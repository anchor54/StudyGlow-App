package com.example.studyglows.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.studyglows.utils.Constants.RUPEE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

    fun<T> List<T>.findIndex(
        predicate: (T) -> Boolean
    ): Int {
        forEachIndexed { i, item ->
            if (predicate(item)) return i
        }
        return -1
    }

    fun<T> List<T>.replace(
        replaceItem: T,
        predicate: (T) -> Boolean
    ): List<T> {
        val newList = this.map { item ->
            if (predicate(item)) replaceItem
            else item
        }
        return newList
    }

    fun<T> List<T>.toShortenedString(
        converter: (T) -> String
    ): String {
        var res = ""
        val len = min(size, 2)
        for (i in 0 until len) {
            res += "${converter(get(i))}${if(i < len - 1) ", " else ""}"
        }
        if (size > 2) {
            res += " and ${size - 2} more..."
        }
        return res
    }

    fun getDate(milliSeconds: Long, dateFormat: String = "dd/MM/yyyy"): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.US)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance().apply {
            timeInMillis = milliSeconds
        }
        return formatter.format(calendar.time)
    }

    fun getTimeInMinsAndSecs(secs: Long): String {
        val mins = secs / 60
        val secsRemaining = secs % 60
        return "${if (mins > 9) mins else "0$mins"}:${if (secsRemaining > 9) secsRemaining else "0$secsRemaining"}"
    }

    fun <T> LiveData<T>.asStateFlow(initialValue: T): StateFlow<T> {
        val stateFlow = MutableStateFlow(initialValue)

        // Observe LiveData and update StateFlow
        this.observeForever { newValue ->
            stateFlow.value = newValue ?: initialValue
        }

        return stateFlow.asStateFlow()
    }

    fun cropText(text: String, maxLength: Int = 30, ellipsis: String = "..."): String {
        // Check if the text needs to be cropped
        return if (text.length > maxLength) {
            // Ensure that the total length respects the maxLength including the ellipsis
            val endIndex = maxLength - ellipsis.length
            text.substring(0, endIndex) + ellipsis
        } else {
            // Return the original text if it's shorter than maxLength
            text
        }
    }
}