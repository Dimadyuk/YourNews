package org.yournews.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import org.yournews.data.NewsApiClient
import org.yournews.data.model.News

@Composable
fun MainScreen() {
    var articles by remember { mutableStateOf(emptyList<News.Article>()) }
    var headerTitle by remember { mutableStateOf("Headlines") }
    var searchedText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(searchedText) {
        scope.launch {
            try {
                val newsData = if (searchedText.isNotBlank()) {
                    NewsApiClient.getSearchedNews(searchedText)
                } else {
                    NewsApiClient.getTopHeadlines()
                }
                articles = newsData.articles
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
                articles = emptyList()
            }
        }
    }

    Row {
        SidePanel(
            onMenuSelected = { header ->
                headerTitle = header
                searchedText = ""
                articles = emptyList()
            },
            onNewsSearched = { _searchedText, _header ->
                searchedText = _searchedText
                headerTitle = _header
                articles = emptyList()
            }
        )
        MainContent(
            headerTitle = headerTitle,
            articles = articles
        )
    }
}
