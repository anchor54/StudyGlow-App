package com.example.studyglows.screens.testseries.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TestSubmitDialog(
    totalQuestions: Int,
    attemptedQuestions: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Are you sure you want to submit?",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF8798AD),
                    letterSpacing = 0.1.sp,
                )
            )
        },
        text = {
            Text(
                text = "You have attempted $attemptedQuestions questions out of $totalQuestions.",
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 20.4.sp,
                    color = Color(0xFF025284),
                    letterSpacing = 0.15.sp,
                )
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("No")
            }
        }
    )
}