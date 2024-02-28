package com.example.studyglows.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyglows.R
import com.example.studyglows.shared.model.SearchResultItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    results: List<SearchResultItem>,
    onResultItemClicked: (id: String) -> Unit,
    onBackClicked: () -> Unit,
    onSearch: (searchTxt: String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val coroutine = rememberCoroutineScope()
    var searchTxt by remember { mutableStateOf(TextFieldValue("")) }
    var job: Job? = null

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF0373BA))) {
        TextField(
            value = searchTxt,
            onValueChange = {
                job?.cancel()
                job = coroutine.launch(Dispatchers.IO) {
                    delay(1000)
                    onSearch(it.text)
                }
                searchTxt = it
            },
            placeholder = { "Search StudyGlows" },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { onSearchClicked(searchTxt.text) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.search),
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
        ) {
            items(results.size) {
                Text(
                    text = results[it].text,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 20.4.sp,
                        color = Color(0xFFE6F1F8),
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .clickable { onResultItemClicked(results[it].id) }
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 10.dp)
                )
            }
        }
    }
}