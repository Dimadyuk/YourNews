package org.yournews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.imageResource
import org.yournews.data.model.News
import org.yournews.utils.handCursor
import org.yournews.utils.loadPicture
import org.yournews.utils.openUrl
import yournews.composeapp.generated.resources.Res
import yournews.composeapp.generated.resources.no_image
import java.net.URI

@Composable
fun MainContent(headerTitle: String, articles: List<News.Article>) {
    if (articles.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.White)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                headerTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(articles) { article ->
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                            .padding(4.dp)
                            .pointerHoverIcon(handCursor())
                            .clickable {
                                if (article.url.isNotEmpty()) {
                                    openUrl(URI(article.url))
                                }
                            },
                        elevation = 4.dp
                    ) {
                        Box {
                            val bitmap = imageResource(Res.drawable.no_image)
                            if (article.urlToImage.isNullOrBlank()) {
                                Image(
                                    bitmap,
                                    contentDescription = "No Image",
                                    modifier = Modifier.size(100.dp).align(Alignment.TopCenter)
                                )
                            } else {
                                Image(
                                    loadPicture(article.urlToImage),
                                    contentDescription = "News Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart)
                                    .background(color = Color.White)
                                    .padding(4.dp)
                            ) {
                                Text(
                                    article.title,
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(
                                    article.description ?: "",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraLight,
                                    fontSize = 14.sp,
                                    maxLines = 3
                                )
                            }
                        }
                    }
                }
            }

        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading...")
        }
    }


}
